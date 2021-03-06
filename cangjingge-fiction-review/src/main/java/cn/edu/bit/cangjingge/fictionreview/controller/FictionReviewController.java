package cn.edu.bit.cangjingge.fictionreview.controller;

import cn.edu.bit.cangjingge.common.entity.FictionReview;
import cn.edu.bit.cangjingge.common.response.Response;
import cn.edu.bit.cangjingge.common.security.RequiresAuthorization;
import cn.edu.bit.cangjingge.fictionreview.service.FictionReviewServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FictionReviewController {

    @Resource
    FictionReviewServiceImpl fictionReviewService;

    @ApiOperation("使用ID获取书评")
    @GetMapping("/id/{id}")
    public Response<FictionReview> getById(
            @PathVariable("id") final Long id
    ) {
        return fictionReviewService.getFictionReviewById(id);
    }

    @ApiOperation("获取一本书的书评")
    @GetMapping("/fiction/{fictionId}")
    public Response<List<FictionReview>> getByFictionId(
            @PathVariable("fictionId") final Long fictionId
    ) {
        return fictionReviewService.getFictionReviewByFictionId(fictionId);
    }

    @ApiOperation("新建书评（需要认证，用户权限及以上）")
    @PostMapping("/{fictionId}/user/{userId}")
    @RequiresAuthorization
    public Response<FictionReview> createReview(
            @PathVariable("fictionId") final Long fictionId,
            @PathVariable("userId") final Long userId,
            final Integer rate,
            final String content
    ) {
        return fictionReviewService.createFictionReview2(fictionId, userId, rate, content);
    }

    @ApiOperation("删除一个书评（需要认证，管理员权限）")
    @DeleteMapping("/id/{id}")
    @RequiresAuthorization({"ROLE_USER", "ROLE_ADMIN"})
    public Response<FictionReview> deleteBookshelfItem(
            @PathVariable("id") final Long id
    ) {
        return fictionReviewService.deleteFictionReviewById(id);
    }

}
