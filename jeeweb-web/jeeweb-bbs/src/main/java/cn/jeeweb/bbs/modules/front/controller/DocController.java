package cn.jeeweb.bbs.modules.front.controller;

import cn.jeeweb.bbs.common.bean.ResponseError;
import cn.jeeweb.bbs.common.jcaptcha.JCaptcha;
import cn.jeeweb.bbs.modules.email.service.IEmailSendService;
import cn.jeeweb.bbs.modules.front.constant.FrontConstant;
import cn.jeeweb.bbs.modules.posts.entity.Posts;
import cn.jeeweb.bbs.modules.posts.entity.PostsComment;
import cn.jeeweb.bbs.modules.posts.service.IPostsCommentService;
import cn.jeeweb.bbs.modules.posts.service.IPostsService;
import cn.jeeweb.bbs.modules.sys.entity.Message;
import cn.jeeweb.bbs.modules.sys.entity.User;
import cn.jeeweb.bbs.modules.sys.service.IMessageService;
import cn.jeeweb.bbs.modules.sys.service.IUserService;
import cn.jeeweb.bbs.security.shiro.credential.RetryLimitHashedCredentialsMatcher;
import cn.jeeweb.bbs.security.shiro.filter.authc.FormAuthenticationFilter;
import cn.jeeweb.bbs.security.shiro.filter.authc.UsernamePasswordToken;
import cn.jeeweb.bbs.utils.SmsVercode;
import cn.jeeweb.bbs.utils.UrlUtils;
import cn.jeeweb.bbs.utils.UserUtils;
import cn.jeeweb.common.http.Response;
import cn.jeeweb.common.mvc.controller.BaseController;
import cn.jeeweb.common.mybatis.mvc.wrapper.EntityWrapper;
import cn.jeeweb.common.utils.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController("FrontDocController")
@RequestMapping("doc")
public class DocController extends BaseController {

	@RequestMapping(value = "/",method = RequestMethod.GET)
	public ModelAndView index(Model model, HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("modules/front/doc/index");
	}
}
