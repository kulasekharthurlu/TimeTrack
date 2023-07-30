package com.app.timetrack.ServiceImpl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.app.timetrack.IService.Test;

@Service

public class Test1 implements Test{

	@Override
	public String getName() {
		return "kulasekkhar";
	}

}
