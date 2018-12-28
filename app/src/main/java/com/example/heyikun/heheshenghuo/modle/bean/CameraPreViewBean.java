package com.example.heyikun.heheshenghuo.modle.bean;

/**
 * 项目:趣租部落
 *
 * @author：location time：2018/11/14 15:40
 * description：
 */

public class CameraPreViewBean {

	/**
	 * status : 200
	 * message : 获取成功
	 * data : {"features_time":"2018/11/14","position1":"https://hehe.heyishenghuo.com/data/headimg/201811/1542136495247345769.jpg","position2":"https://hehe.heyishenghuo.com/data/headimg/201811/1542136495581234516.jpg","results":""}
	 */

	private String status;
	private String message;
	private DataBean data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * features_time : 2018/11/14
		 * position1 : https://hehe.heyishenghuo.com/data/headimg/201811/1542136495247345769.jpg
		 * position2 : https://hehe.heyishenghuo.com/data/headimg/201811/1542136495581234516.jpg
		 * results :
		 */

		private String features_time;
		private String position1;
		private String position2;
		private String results;

		public String getFeatures_time() {
			return features_time;
		}

		public void setFeatures_time(String features_time) {
			this.features_time = features_time;
		}

		public String getPosition1() {
			return position1;
		}

		public void setPosition1(String position1) {
			this.position1 = position1;
		}

		public String getPosition2() {
			return position2;
		}

		public void setPosition2(String position2) {
			this.position2 = position2;
		}

		public String getResults() {
			return results;
		}

		public void setResults(String results) {
			this.results = results;
		}
	}
}
