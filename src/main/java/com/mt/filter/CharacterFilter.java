package com.mt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CharacterFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("=ali==CharacterFilter===");
		filterChain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
		
	}

}
