package com.example.heyikun.heheshenghuo.modle.bean;

/**
 * 项目:趣租部落
 *
 * @author：location time：2018/11/28 18:36
 * description：
 */

public class SendMessageBean {

	/**
	 * status : 200
	 * message : 发送成功
	 * data : {"type":"vote","content":{"id":"2288","name":"咋回事啊","type":"3","sort":"1","vote_id":"324","val_scope":"{\"\":0}","tishiyu":""}}
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
		 * type : vote
		 * content : {"id":"2288","name":"咋回事啊","type":"3","sort":"1","vote_id":"324","val_scope":"{\"\":0}","tishiyu":""}
		 */

		private String type;
		private ContentBean content;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public ContentBean getContent() {
			return content;
		}

		public void setContent(ContentBean content) {
			this.content = content;
		}

		public static class ContentBean {
			/**
			 * id : 2288
			 * name : 咋回事啊
			 * type : 3
			 * sort : 1
			 * vote_id : 324
			 * val_scope : {"":0}
			 * tishiyu :
			 */

			private String id;
			private String name;
			private String type;
			private String sort;
			private String vote_id;
			private String val_scope;
			private String tishiyu;

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public String getSort() {
				return sort;
			}

			public void setSort(String sort) {
				this.sort = sort;
			}

			public String getVote_id() {
				return vote_id;
			}

			public void setVote_id(String vote_id) {
				this.vote_id = vote_id;
			}

			public String getVal_scope() {
				return val_scope;
			}

			public void setVal_scope(String val_scope) {
				this.val_scope = val_scope;
			}

			public String getTishiyu() {
				return tishiyu;
			}

			public void setTishiyu(String tishiyu) {
				this.tishiyu = tishiyu;
			}
		}
	}
}
