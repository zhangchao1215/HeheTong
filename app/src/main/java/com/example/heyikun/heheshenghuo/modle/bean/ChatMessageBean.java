package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * 项目:趣租部落
 *
 * @author：location time：2018/11/28 14:09
 * description：发送及时消息实体类
 */

public class ChatMessageBean {


	/**
	 * status : 200
	 * message : 获取成功
	 * data : [{"chat_staff":"148","chat_user":"5947","chat_sender":"0","chat_type":"2","chat_vote":"329","chat_ask":"2296","chat_content":"有痛风吗？","chat_read":"1","chat_time":"2018-11-29 17:56:54","send_headimg":"","vote_ask":{"id":"2296","name":"有痛风吗？","type":"1","sort":"2","vote_id":"329","val_scope":[{"name":"有"},{"name":"没有"}],"tishiyu":""}},{"chat_staff":"148","chat_user":"5947","chat_sender":"2","chat_type":"0","chat_vote":"0","chat_ask":"0","chat_content":"选择题","chat_read":"0","chat_time":"2018-11-29 17:56:53","send_headimg":"https://hehe.heyishenghuo.com/data/headimg/201806/1529632676875410428.jpg"},{"chat_staff":"148","chat_user":"5947","chat_sender":"0","chat_type":"2","chat_vote":"328","chat_ask":"2294","chat_content":"疼多少时间","chat_read":"1","chat_time":"2018-11-29 17:50:14","send_headimg":""},{"chat_staff":"148","chat_user":"5947","chat_sender":"2","chat_type":"0","chat_vote":"0","chat_ask":"0","chat_content":"头疼","chat_read":"0","chat_time":"2018-11-29 17:50:13","send_headimg":"https://hehe.heyishenghuo.com/data/headimg/201806/1529632676875410428.jpg"},{"chat_staff":"148","chat_user":"5947","chat_sender":"0","chat_type":"2","chat_vote":"329","chat_ask":"2295","chat_content":"你说你咋了","chat_read":"1","chat_time":"2018-11-29 17:26:02","send_headimg":""},{"chat_staff":"148","chat_user":"5947","chat_sender":"2","chat_type":"3","chat_vote":"329","chat_ask":"0","chat_content":"有","chat_read":"0","chat_time":"2018-11-29 17:26:01","send_headimg":"https://hehe.heyishenghuo.com/data/headimg/201806/1529632676875410428.jpg"},{"chat_staff":"148","chat_user":"5947","chat_sender":"0","chat_type":"2","chat_vote":"329","chat_ask":"2296","chat_content":"有痛风吗？","chat_read":"1","chat_time":"2018-11-29 17:17:59","send_headimg":""},{"chat_staff":"148","chat_user":"5947","chat_sender":"2","chat_type":"0","chat_vote":"0","chat_ask":"0","chat_content":"选择题","chat_read":"0","chat_time":"2018-11-29 17:17:58","send_headimg":"https://hehe.heyishenghuo.com/data/headimg/201806/1529632676875410428.jpg"}]
	 */

	private String status;
	private String message;
	private List<DataBean> data;

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

	public List<DataBean> getData() {
		return data;
	}

	public void setData(List<DataBean> data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * chat_staff : 148
		 * chat_user : 5947
		 * chat_sender : 0
		 * chat_type : 2
		 * chat_vote : 329
		 * chat_ask : 2296
		 * chat_content : 有痛风吗？
		 * chat_read : 1
		 * chat_time : 2018-11-29 17:56:54
		 * send_headimg :
		 * vote_ask : {"id":"2296","name":"有痛风吗？","type":"1","sort":"2","vote_id":"329","val_scope":[{"name":"有"},{"name":"没有"}],"tishiyu":""}
		 */

		private String chat_staff;
		private String chat_user;
		private String chat_sender;
		private String chat_type;
		private String chat_vote;
		private String chat_ask;
		private String chat_content;
		private String chat_read;
		private String chat_time;
		private String send_headimg;
		private String chat_image; //图片类型
		private String chat_text;
		private String chat_id;
		private VoteAskBean vote_ask;

		public String getChat_id() {
			return chat_id;
		}

		public void setChat_id(String chat_id) {
			this.chat_id = chat_id;
		}

		public String getChat_text() {
			return chat_text;
		}

		public void setChat_text(String chat_text) {
			this.chat_text = chat_text;
		}

		public String getChat_image() {
			return chat_image;
		}

		public void setChat_image(String chat_image) {
			this.chat_image = chat_image;
		}

		public String getChat_staff() {
			return chat_staff;
		}

		public void setChat_staff(String chat_staff) {
			this.chat_staff = chat_staff;
		}

		public String getChat_user() {
			return chat_user;
		}

		public void setChat_user(String chat_user) {
			this.chat_user = chat_user;
		}

		public String getChat_sender() {
			return chat_sender;
		}

		public void setChat_sender(String chat_sender) {
			this.chat_sender = chat_sender;
		}

		public String getChat_type() {
			return chat_type;
		}

		public void setChat_type(String chat_type) {
			this.chat_type = chat_type;
		}

		public String getChat_vote() {
			return chat_vote;
		}

		public void setChat_vote(String chat_vote) {
			this.chat_vote = chat_vote;
		}

		public String getChat_ask() {
			return chat_ask;
		}

		public void setChat_ask(String chat_ask) {
			this.chat_ask = chat_ask;
		}

		public String getChat_content() {
			return chat_content;
		}

		public void setChat_content(String chat_content) {
			this.chat_content = chat_content;
		}

		public String getChat_read() {
			return chat_read;
		}

		public void setChat_read(String chat_read) {
			this.chat_read = chat_read;
		}

		public String getChat_time() {
			return chat_time;
		}

		public void setChat_time(String chat_time) {
			this.chat_time = chat_time;
		}

		public String getSend_headimg() {
			return send_headimg;
		}

		public void setSend_headimg(String send_headimg) {
			this.send_headimg = send_headimg;
		}

		public VoteAskBean getVote_ask() {
			return vote_ask;
		}

		public void setVote_ask(VoteAskBean vote_ask) {
			this.vote_ask = vote_ask;
		}

		public static class VoteAskBean {
			/**
			 * id : 2296
			 * name : 有痛风吗？
			 * type : 1
			 * sort : 2
			 * vote_id : 329
			 * val_scope : [{"name":"有"},{"name":"没有"}]
			 * tishiyu :
			 */

			private String id;
			private String name;
			private String type;
			private String sort;
			private String vote_id;
			private String tishiyu;
			private List<ValScopeBean> val_scope;

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

			public String getTishiyu() {
				return tishiyu;
			}

			public void setTishiyu(String tishiyu) {
				this.tishiyu = tishiyu;
			}

			public List<ValScopeBean> getVal_scope() {
				return val_scope;
			}

			public void setVal_scope(List<ValScopeBean> val_scope) {
				this.val_scope = val_scope;
			}

			public static class ValScopeBean {
				/**
				 * name : 有
				 */

				private String name;

				public String getName() {
					return name;
				}

				public void setName(String name) {
					this.name = name;
				}
			}
		}
	}
}
