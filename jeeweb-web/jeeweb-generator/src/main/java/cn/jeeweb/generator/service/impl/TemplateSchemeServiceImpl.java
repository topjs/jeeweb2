package cn.jeeweb.generator.service.impl;

import cn.jeeweb.common.mybatis.mvc.service.impl.CommonServiceImpl;
import cn.jeeweb.generator.entity.TemplateScheme;
import cn.jeeweb.generator.mapper.TemplateSchemeMapper;
import cn.jeeweb.generator.service.ITemplateSchemeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 模板方案
 * @Description: 模板方案
 * @author 王存见
 * @date 2017-09-15 15:21:43
 * @version V1.0   
 *
 */
@Transactional
@Service("templateSchemeService")
public class TemplateSchemeServiceImpl  extends CommonServiceImpl<TemplateSchemeMapper,TemplateScheme> implements ITemplateSchemeService {

}
