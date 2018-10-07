package cn.jeeweb.generator.service.impl;

import cn.jeeweb.common.mybatis.mvc.service.impl.CommonServiceImpl;
import cn.jeeweb.generator.entity.Template;
import cn.jeeweb.generator.mapper.TemplateMapper;
import cn.jeeweb.generator.service.ITemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 生成模板
 * @Description: 生成模板
 * @author jeeweb
 * @date 2017-09-15 15:10:12
 * @version V1.0   
 *
 */
@Transactional
@Service("templateService")
public class TemplateServiceImpl  extends CommonServiceImpl<TemplateMapper,Template> implements ITemplateService {

}
