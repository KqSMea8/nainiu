package com.jeecg.websocket;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.p3.core.utils.common.ApplicationContextUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jeecg.chat.entity.ChatMessageHistory;
import com.jeecg.chat.service.ChatMessageHistoryService;
import com.jeecg.demo.service.JeecgDemoServiceI;
import com.jeecg.n_websocket.entity.NWebsocketEntity;
import com.jeecg.n_websocket_colum.entity.NWebsocketColumEntity;


/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
/*@Controller
@RequestMapping("/websocket")
public class WSServer extends BaseController{*/

@ServerEndpoint("/websocket")
public class WSServer {
	private String name;
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
 //   private static CopyOnWriteArraySet<WSServer> webSocketSet = new CopyOnWriteArraySet<WSServer>();
	/*保存连接的MAP容器*/
	private static final Map<String,Session> userconnections = new HashMap<>();
//	private static final Map<String,String> userstr = new HashMap<String,String>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
	 SystemService  systemservice;
	
	
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        systemservice = ApplicationContextUtil.getContext().getBean(
                SystemService.class);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
    	//userLeave(conn);
     //   webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        JSONObject json = JSON.parseObject(message);
        String type=json.get("type").toString();
	    JSONObject jsonObject = (JSONObject)json.get("data");
	    String sendid = jsonObject.get("sendid").toString();
	    String sendname = jsonObject.get("sendname").toString();
	    String acceptid = jsonObject.get("acceptid").toString();
	    String acceptname = jsonObject.get("acceptname").toString();
	    String details = jsonObject.get("details").toString();
	    String goodid = jsonObject.get("goodid").toString();
	    String socketid = jsonObject.get("socketid").toString();
	    String accepturl = jsonObject.get("accepturl").toString();
	    String sendurl = jsonObject.get("sendurl").toString();
        //自己
        System.out.println("=sendid=="+sendid+"=acceptid=="+acceptid+"="+socketid);
      
        if("send".equals(type)){
	        
	        try {
	        	 //details=sendid+":"+details;
	        	Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        	JSONObject object = new JSONObject();
		        	object.put("sendid", sendid);
		        	object.put("sendurl",sendurl);
		        	object.put("accepturl", accepturl);
		        	object.put("create_date", f.format(new Date()));
		        	object.put("message", details);
	        	 sendMessage(session,object.toString());
	        	 if(userconnections.containsKey(acceptid+socketid)){
	        		 sendMessage(userconnections.get(acceptid+socketid),object.toString());
	        		 getint( sendid, sendname, acceptid,acceptname,socketid,details,goodid,sendurl,accepturl,"1");
	        	 }else{
	        		 getint( sendid, sendname, acceptid,acceptname,socketid,details,goodid,sendurl,accepturl,"0");
	        	 }
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if("open".equals(type)){
	        userconnections.put(sendid+socketid,session);
	    	getintcolum(sendid, sendname, acceptid,acceptname,socketid,details,goodid,sendurl,accepturl);
	      // userconnections.put(acceptid,session);	//添加连接
        }else if("colse".equals(sendid)){
        	if(userconnections.containsKey(sendid+socketid)){
    			userconnections.remove(sendid+socketid);	
        	}
        }
       
    }
   // 聊天记录
    public void getint(String  sendid,String sendname,String acceptid,
    		String acceptname,String socketid,String details,String goodid,String sendurl,String accepturl,String flag){
			NWebsocketColumEntity nwebsocket=new NWebsocketColumEntity();
			nwebsocket.setSendid(sendid);
			nwebsocket.setSendname(sendname);
			nwebsocket.setAcceptid(acceptid);
			nwebsocket.setAcceptname(acceptname);
			nwebsocket.setSocketid(socketid);
			nwebsocket.setDetails(details);
			nwebsocket.setSendurl(sendurl);
			nwebsocket.setAccepturl(accepturl);
			nwebsocket.setFlag(flag);//0未读//1已读
			nwebsocket.setCreateDate(new Date());
			systemservice.save(nwebsocket);
		    	
    }
    /**聊天目录*/
    public void getintcolum(String  sendid,String sendname,String acceptid,
    		String acceptname,String socketid,String details,String goodid,String sendurl,String accepturl){
    	StringBuffer sql = new StringBuffer();
		sql.append("select id,sendid,acceptid from n_websocket_colum where 1=1 ")
    	    .append(" and sendid='").append(sendid).append("'")
			.append(" and acceptid='").append(acceptid).append("'");
    	List<Object[]> mode_list = systemservice.findListbySql(sql.toString());
    	NWebsocketColumEntity nwebsocket=new NWebsocketColumEntity();
    	nwebsocket.setSendid(sendid);
    	nwebsocket.setSendname(sendname);
    	nwebsocket.setAcceptid(acceptid);
    	nwebsocket.setAcceptname(acceptname);
    	nwebsocket.setSendid(sendid);
    	nwebsocket.setSocketid(socketid);
    	nwebsocket.setDetails(details);
      	nwebsocket.setSendurl(sendurl);
    	nwebsocket.setAccepturl(accepturl);
    	nwebsocket.setFlag("1");//0未读//1已读
    	nwebsocket.setCreateDate(new Date());
    	nwebsocket.setType("0");//0聊天，1是推送
    	if(mode_list.size()<1){
		    	systemservice.save(nwebsocket);
    	}else{
    		String id=mode_list.get(0)[0]+"";
    		if(!StringUtil.is_khEmpty(id)){
	    		nwebsocket.setId(mode_list.get(0)[0]+"");
	    		systemservice.saveOrUpdate(nwebsocket);
    		}
    	}
    }
   

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(Session session,String  message) throws IOException{
//        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    	session.getBasicRemote().sendText(message);
    }
    public void sendMessage(String message)
    	    throws IOException
    	  {
    	    this.session.getBasicRemote().sendText(message);
    	  }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WSServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WSServer.onlineCount--;
    }
 

	
	
	
	
	

}