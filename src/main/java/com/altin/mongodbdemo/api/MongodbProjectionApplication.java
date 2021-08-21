package com.altin.mongodbdemo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@SpringBootApplication
public class MongodbProjectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbProjectionApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<OncePerRequestFilter> executionTimeLoggingFilter(){
		return new FilterRegistrationBean<OncePerRequestFilter>(){{
			setUrlPatterns(getUrlPatterns());
			setOrder(OrderedFilter.REQUEST_WRAPPER_FILTER_MAX_ORDER);
			setFilter(new OncePerRequestFilter() {
				@Override
				protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
					StopWatch watch = new StopWatch();
					watch.start();
					try {
						chain.doFilter(req, res);

					}finally {
						watch.stop();
						//log the time.
						log.info("REQUEST: {} completed within {} ms", getUriMethodWithMethodAndQuery(req), watch.getTotalTimeMillis());
					}
				}

				private String getUriMethodWithMethodAndQuery(HttpServletRequest req) {
					return req.getMethod() + ": " + req.getRequestURI() +
							(StringUtils.hasText(req.getQueryString()) ? "?" + req.getQueryString() : "");

				}
			});
		}};
	}
}
