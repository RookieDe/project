package com.project.project5.web.controller.test;

import com.project.project5.web.controller.base.BaseController;
import com.project.project5.entity.Response;
import com.project.project5.entity.mongodb.Book;
import com.project.project5.enums.ExceptionEnums;
import com.project.project5.service.impl.MongoTestService;
import com.project.project5.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName MongoTestController
 * @date 2020/4/14 11:39
 */
@RestController
@Api(value = "mongoTest", description = "测试mongo类相关接口（RookieDe）")
@RequestMapping(value = "mongoTest", produces = "application/json;charset=utf-8")
public class MongoTestController extends BaseController {

    @Autowired
    private MongoTestService mongoTestService;


    @ApiOperation("新增")
    @PostMapping("/insert")
    public Object insert(@RequestBody Book book) {
        try {
            mongoTestService.insert(book);
            return ResultUtils.success(ExceptionEnums.SUCCESS.getCode());
        } catch (Exception e) {
            return ResultUtils.error(ExceptionEnums.FAILED.getCode(), e.getMessage());
        }
    }

    @ApiOperation("查询所有")
    @GetMapping("/findAll")
    public Response<List<Book>> findAll() {
        try {
            return ResultUtils.success(mongoTestService.findAll());
        } catch (Exception e) {
            return ResultUtils.error(ExceptionEnums.FAILED.getCode(), e.getMessage());
        }
    }

    @ApiOperation("查询单条")
    @GetMapping("/findOne")
    public Response<Book> findOne(@RequestParam String bookId) {
        try {
            return ResultUtils.success(mongoTestService.getBookById(bookId));
        } catch (Exception e) {
            return ResultUtils.error(ExceptionEnums.FAILED.getCode(), e.getMessage());
        }
    }

    @ApiOperation("通过姓名查询信息")
    @GetMapping("/findOneByName")
    public Response<Book> findOneByName(@RequestParam String name) {
        try {
            return ResultUtils.success(mongoTestService.getBookByName(name));
        } catch (Exception e) {
            return ResultUtils.error(ExceptionEnums.FAILED.getCode(), e.getMessage());
        }
    }

    @ApiOperation("更新")
    @PostMapping("/update")
    public Response<String> update(@RequestBody Book book) {
        try {
            return ResultUtils.success(mongoTestService.updateBook(book));
        } catch (Exception e) {
            return ResultUtils.error(ExceptionEnums.FAILED.getCode(), e.getMessage());
        }
    }

    @ApiOperation("删除信息")
    @PostMapping("/delOne")
    public Response<String> delOne(@RequestBody Book book) {
        try {
            return ResultUtils.success(mongoTestService.deleteBook(book));
        } catch (Exception e) {
            return ResultUtils.error(ExceptionEnums.FAILED.getCode(), e.getMessage());
        }
    }

    @ApiOperation("根据id删除信息")
    @GetMapping("/delById")
    public Response<String> delById(@RequestParam String bookId) {
        try {
            return ResultUtils.success(mongoTestService.deleteBookById(bookId));
        } catch (Exception e) {
            return ResultUtils.error(ExceptionEnums.FAILED.getCode(), e.getMessage());
        }
    }

    @ApiOperation("根据查询条件查询信息")
    @GetMapping("/findlikes")
    public Response<List<Book>> findByLikes(@RequestParam String search) {
        try {
            return ResultUtils.success(mongoTestService.findByLikes(search));
        } catch (Exception e) {
            return ResultUtils.error(ExceptionEnums.FAILED.getCode(), e.getMessage());
        }
    }


}
