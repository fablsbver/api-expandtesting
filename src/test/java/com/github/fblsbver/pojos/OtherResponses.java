package com.github.fblsbver.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherResponses{
	private boolean success;
	private String message;
	private int status;
}
