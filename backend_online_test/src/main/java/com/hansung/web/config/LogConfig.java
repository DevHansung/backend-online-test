package com.hansung.web.config;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class LogConfig {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Around("execution(* com.hansung.web.service..*.get*(..))")
	public Object getLogging(ProceedingJoinPoint pjp) throws Throwable { // 2
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		Random random = new Random();
		int randomOne = Math.abs(random.nextInt(1000) + 1);
		int randomTwo = Math.abs(random.nextInt(1000) + 1);
		long randomNumber = System.currentTimeMillis() + randomOne + randomTwo;
		try {
			long startAt = System.currentTimeMillis();

			Object result = pjp.proceed();

			String requestAndResponseDatas = getRequestAndResponseDatas(pjp, request, randomNumber);
			long endAt = System.currentTimeMillis();
			log.info("GET_SUCCESS : [{}, 실행시간 : ({}ms)]", requestAndResponseDatas, endAt - startAt);
			return result;
		} catch (Exception e) {
			log.error("ERROR: [요청 처리번호 : ({}), error_message : ({})]", randomNumber, e.getMessage());
		}
		return null;
	}

	@Around("execution(* com.hansung.web.service..*.insert*(..)) ")
	public Object insertAndUpdateAndDeletelogging(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		Random random = new Random();
		int randomOne = Math.abs(random.nextInt(1000) + 1);
		int randomTwo = Math.abs(random.nextInt(1000) + 1);
		long randomNumber = System.currentTimeMillis() + randomOne + randomTwo;
		if (request.getHeader("name") != null) {
			try {
				String requestDatas = getRequestDatas(pjp, request, randomNumber);
				long startAt = System.currentTimeMillis();
				log.info("REQUEST : [{}]", requestDatas);

				Object result = pjp.proceed(); // 이부분을 기점으로 실행 전과 후가 나뉨.

				String responseDatas = getResponseDatas(pjp, request, randomNumber);
				long endAt = System.currentTimeMillis();
				log.info("RESPONSE : [{}, 실행시간 : ({}ms)]", responseDatas, endAt - startAt);
				return result;
			} catch (Exception e) {
				log.error("ERROR: [요청 처리번호 : ({}), error_message : ({})]", randomNumber, e.getMessage());
			}
		}
		return pjp.proceed();
	}

	
	private String getRequestAndResponseDatas(ProceedingJoinPoint pjp, HttpServletRequest request, long randomNumber) {
		Map<String, Object> datas = new LinkedHashMap<>();
		try {
			if (request.getUserPrincipal() != null) {
				datas.put("요청유저명", request.getUserPrincipal().getName());
			}
			datas.put("요청처리번호", randomNumber);
			datas.put("요청주소", request.getRequestURI());
		} catch (NullPointerException e) {
			log.error("LoggerAspect error", e);
		}
		return datas.entrySet().stream().map(entry -> String.format("%s : (%s)", entry.getKey(), entry.getValue()))
				.collect(Collectors.joining(", "));
	}

	private String getRequestDatas(ProceedingJoinPoint pjp, HttpServletRequest request, long randomNumber) {
		Map<String, Object> reqDatas = new LinkedHashMap<>();
		try {
			reqDatas.put("요청유저명", request.getHeader("name"));
			reqDatas.put("요청처리번호", randomNumber);
			reqDatas.put("요청주소", request.getRequestURI());
			reqDatas.put("HTTP요청메소드", request.getMethod());
			Enumeration<String> reqParams = request.getParameterNames();
			while (reqParams.hasMoreElements()) {
				String name = (String) reqParams.nextElement();
				reqDatas.put(name, request.getParameter(name));
			}
		} catch (NullPointerException e) {
			log.error("LoggerAspect error", e);
		}
		return reqDatas.entrySet().stream().map(entry -> String.format("%s : (%s)", entry.getKey(), entry.getValue()))
				.collect(Collectors.joining(", "));
	}

	private String getResponseDatas(ProceedingJoinPoint pjp, HttpServletRequest request, long randomNumber) {
		Map<String, Object> resDatas = new LinkedHashMap<>();
		try {
			resDatas.put("요청유저명", request.getHeader("name"));
			resDatas.put("요청처리번호", randomNumber);
		} catch (NullPointerException e) {
			log.error("LoggerAspect error", e);
		}
		return resDatas.entrySet().stream().map(entry -> String.format("%s : (%s)", entry.getKey(), entry.getValue()))
				.collect(Collectors.joining(", "));
	}
}