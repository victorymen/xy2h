package com.xy2h.portal.service;

import com.xy2h.pojo.TbUser;

public interface UserService {

	TbUser getUserByToken(String token);
}
