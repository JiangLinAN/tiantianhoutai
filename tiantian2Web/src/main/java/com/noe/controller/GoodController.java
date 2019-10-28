package com.noe.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.noe.pojo.Good;
import com.noe.service.GoodService;
import com.noe.utils.MyStatus;
import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Author:nore
 */
@Controller
@RequestMapping("/goods")
@RequiresAuthentication
public class GoodController {
    @Autowired
    private GoodService goodService;

    @GetMapping("/allGoods")
    public String findAllGoods(String num,HttpServletRequest request){
        Integer pageNum=Integer.parseInt(num);
        if (pageNum<1){
            pageNum=1;
        }
        PageHelper.startPage(pageNum,7);
        List<Good> allGood = goodService.findAllGood();
        PageInfo<Good> pageInfo=new PageInfo<>(allGood);
        request.setAttribute("pageInfo",pageInfo);
        return "WEB-INF/index";
    }

    @GetMapping("queryGoodById")
    public String queryGoodById(Integer id,HttpServletRequest request){
        System.out.println(id);
        Good good = goodService.queryGoodById(id);
        request.setAttribute("good",good);
        return "WEB-INF/update";
    }

    @GetMapping("/dele")
    public String dele(Integer id){
        goodService.deleGoodById(id);
        return "WEB-INF/index";
    }

    @PostMapping("/addPicture")
    public void addPicture(MultipartFile source, HttpSession session,HttpServletResponse response) throws IOException {
        System.out.println("添加图片");
        //文件的原始名称
        String filename = source.getOriginalFilename();
        System.out.println("原始名称"+filename);
        //定制全局唯一的命名
        String unique = UUID.randomUUID().toString();
        //获得文件的后缀
        String ext = FilenameUtils.getExtension(filename);//abc.txt   txt    hello.html  html
        //定制全局唯一的文件名
        String uniqueFileName = unique+"."+ext;
        System.out.println("唯一的文件名:"+uniqueFileName);

        //文件的类型
        String type = source.getContentType();
        System.out.println("filename:"+filename+" type:"+type);

        //获得 upload_file的磁盘路径 ==> 在webapp目录下创建一个目录"upload_file",且此目录初始不要为空，否则编译时被忽略
        String real_path = session.getServletContext().getRealPath("/image");
        System.out.println("real_path:"+real_path);

        //将上传的文件，存入磁盘路径中
        //source.transferTo(new File("d:/xxxx/xxxx/xx.jpg"))
        source.transferTo(new File(real_path+"\\"+uniqueFileName));
        response.getWriter().print(uniqueFileName);
    }
}
