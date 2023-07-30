package com.app.timetrack.ServiceImpl;

import org.springframework.stereotype.Service;

import com.app.timetrack.IService.Test;

@Service
 
public class Test2 implements Test{

	@Override
	public String getName() {
		return "Java developer";
	}

}
