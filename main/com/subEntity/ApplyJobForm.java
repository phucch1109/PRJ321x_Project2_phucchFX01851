package com.subEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ApplyJobForm {
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String userName;
}
