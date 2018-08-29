package com.zz.opensdk.web.velocity;

import org.apache.velocity.context.Context;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class VelocityLayoutView extends org.springframework.web.servlet.view.velocity.VelocityLayoutView {
	private Map<String, Object> velocityTools;

	protected void doRender(Context context, HttpServletResponse response) throws Exception {
		for (Map.Entry<String, Object> entry : velocityTools.entrySet()) {
			context.put(entry.getKey(), entry.getValue());
		}
		super.doRender(context, response);
	}

	public void setVelocityTools(Map<String, Object> velocityTools) {
		this.velocityTools = velocityTools;
	}
}
