package com.metis.book.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditAwareImpl  implements AuditorAware<Long> {

	@Override
	public Optional<Long> getCurrentAuditor() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
