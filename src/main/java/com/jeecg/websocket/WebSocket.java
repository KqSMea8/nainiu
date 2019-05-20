package com.jeecg.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jeecg.chat.entity.ChatMessageHistory;
import com.jeecg.chat.service.ChatMessageHistoryService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.jeecgframework.p3.core.logger.Logger;
import org.jeecgframework.p3.core.logger.LoggerFactory;
import org.jeecgframework.p3.core.utils.common.ApplicationContextUtil;
import org.springframework.context.ApplicationContext;

@ServerEndpoint("/WebSocket/id")
public class WebSocket
{
  private static final Logger logger = LoggerFactory.getLogger(WebSocket.class);

  private static int onlineCount = 0;

  private static Map<String, WebSocket> webSocketHashMap = new HashMap();
  private Session session;
  @SuppressWarnings("unused")
private ChatMessageHistoryService chatMessageHistoryService;

  @OnOpen
  public void onOpen(@PathParam("id") String id, Session session)
  {
	  System.out.println("232=="+id);
    this.session = session;
    webSocketHashMap.put(id, this);
    System.out.println("有新连接加入12！当前在线人数为" + getOnlineCount());
    addOnlineCount();
    logger.info("有新连接加入！当前在线人数为" + getOnlineCount());

    ApplicationContext ctx = ApplicationContextUtil.getContext();
    if (ctx.containsBean("chatMessageHistoryService"))
      this.chatMessageHistoryService = ((ChatMessageHistoryService)ctx.getBean("chatMessageHistoryService"));
  }

  @OnClose
  public void onClose()
  {
    webSocketHashMap.remove(this);
    subOnlineCount();
    logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
  }

  @OnMessage
  public void onMessage(String message, Session session)
  {
    logger.info("来自客户端的消息:" + message);
    JSONObject json = JSON.parseObject(message);
    JSONObject jsonObject = (JSONObject)json.get("data");
    String to = jsonObject.get("to").toString();
    String from = jsonObject.get("from").toString();
    String msg = jsonObject.get("msg").toString();
    String type = json.get("type").toString();
     System.out.println(to+"=from="+from);
   /* ChatMessageHistory messageHistory = new ChatMessageHistory();
    messageHistory.setFromName(jsonObject.getString("fromName"));
    messageHistory.setFromUser(jsonObject.getString("from"));
    messageHistory.setToName(jsonObject.getString("toName"));
    messageHistory.setToUser(jsonObject.getString("to"));
    messageHistory.setMsgType(json.getString("type"));
    messageHistory.setMsg(jsonObject.getString("msg"));
    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    messageHistory.setId(randomSeed);
    this.chatMessageHistoryService.insert(messageHistory);*/
    try
    {
      for (String key : webSocketHashMap.keySet()) {
        if (key.equals(to)) {
          ((WebSocket)webSocketHashMap.get(key)).sendMessage(message);
        }
      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @OnError
  public void onError(Session session, Throwable error)
  {
    logger.info("发生错误");
    error.printStackTrace();
  }

  public void sendMessage(String message)
    throws IOException
  {
    this.session.getBasicRemote().sendText(message);
  }

  public static synchronized int getOnlineCount()
  {
    return onlineCount;
  }

  public static synchronized void addOnlineCount() {
    onlineCount += 1;
  }

  public static synchronized void subOnlineCount() {
    onlineCount -= 1;
  }
}