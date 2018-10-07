package cn.jeeweb.generator.controller;

import cn.jeeweb.common.http.PageResponse;
import cn.jeeweb.common.http.Response;
import cn.jeeweb.common.mvc.annotation.ViewPrefix;
import cn.jeeweb.common.mvc.controller.BaseBeanController;
import cn.jeeweb.common.query.annotation.PageableDefaults;
import cn.jeeweb.common.query.data.PropertyPreFilterable;
import cn.jeeweb.common.query.data.Queryable;
import cn.jeeweb.common.query.utils.QueryableConvertUtils;
import cn.jeeweb.common.mybatis.mvc.wrapper.EntityWrapper;
import cn.jeeweb.common.utils.ObjectUtils;
import cn.jeeweb.common.utils.StringUtils;
import cn.jeeweb.generator.entity.TemplateScheme;
import cn.jeeweb.generator.service.ITemplateSchemeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**   
 * @Title: 模板方案
 * @Description: 模板方案
 * @author 王存见
 * @date 2017-09-15 15:21:43
 * @version V1.0   
 *
 */
@RestController
@RequestMapping("${admin.url.prefix}/generator/template/scheme")
@ViewPrefix("modules/generator/template")
public class TemplateSchemeController extends BaseBeanController<TemplateScheme> {

    @Autowired
    protected ITemplateSchemeService templateSchemeService;

    public TemplateScheme get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return templateSchemeService.selectById(id);
        } else {
            return newModel();
        }
    }

    @GetMapping
    public ModelAndView list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return displayModelAndView("scheme_list");
    }

    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<TemplateScheme> entityWrapper = new EntityWrapper<TemplateScheme>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageResponse<TemplateScheme> pagejson = new PageResponse<TemplateScheme>(templateSchemeService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

    @GetMapping(value = "add")
    public ModelAndView add(Model model, HttpServletRequest request, HttpServletResponse response) {
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", newModel());
        }
        return displayModelAndView("scheme_edit");
    }

    @PostMapping(value = "add")
    public Response add(Model model, @Valid @ModelAttribute("data") TemplateScheme templateScheme, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        // 验证错误
        this.checkError(templateScheme,result);
        templateSchemeService.insert(templateScheme);
        return Response.ok("添加成功");
    }

    @GetMapping(value = "{id}/update")
    public ModelAndView update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                         HttpServletResponse response) {
        TemplateScheme templateScheme = get(id);
        model.addAttribute("data", templateScheme);
        return displayModelAndView("scheme_edit");
    }

    @PostMapping(value = "{id}/update")
    public Response update(Model model, @Valid @ModelAttribute("data") TemplateScheme templateScheme, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        // 验证错误
        this.checkError(templateScheme,result);
        templateSchemeService.insertOrUpdate(templateScheme);
        return Response.ok("更新成功");
    }

    @PostMapping(value = "{id}/delete")
    public Response delete(@PathVariable("id") String id) { ;
        try {
            templateSchemeService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error("删除失败");
        }
        return  Response.ok("删除成功");
    }

    @PostMapping(value = "batch/delete")
    public Response batchDelete(@RequestParam(value = "ids", required = false) String[] ids) {
        try {
            List<String> idList = java.util.Arrays.asList(ids);
            templateSchemeService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error("删除失败");
        }
        return Response.ok("删除成功");
    }
}
