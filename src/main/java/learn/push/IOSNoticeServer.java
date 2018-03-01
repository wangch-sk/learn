package learn.push;
//import java.util.ArrayList;
//import java.util.List;
//
//import javapns.devices.Device;
//import javapns.devices.implementations.basic.BasicDevice;
//import javapns.notification.AppleNotificationServerBasicImpl;
//import javapns.notification.PushNotificationManager;
//import javapns.notification.PushNotificationPayload;
//import javapns.notification.PushedNotification;
//
//public class IOSNoticeServer {
//	public void sendpush(List<String> tokens, String path, String password, String message, Integer count,
//			boolean sendCount) {
//		try {
//			PushNotificationPayload payLoad = PushNotificationPayload.fromJSON(message);
//			payLoad.addBadge(count);
//			payLoad.addSound("default");
//			PushNotificationManager pushManager = new PushNotificationManager();
//			pushManager.initializeConnection(new AppleNotificationServerBasicImpl(path, password, false));
//			List<PushedNotification> notifications = new ArrayList<PushedNotification>();
//			List<Device> device = new ArrayList<Device>();
//			for (String token : tokens) {
//				device.add(new BasicDevice(token));
//			}
//			notifications = pushManager.sendNotifications(payLoad, device);
//
//			List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
//
//			List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
//
//			int failed = failedNotifications.size();
//			int successful = successfulNotifications.size();
//			
//			System.out.println();
//			System.out.println("successful=" + successful);
//			System.out.println("failed=" + failed);
//			if (failed > 0) {
//				//这里是失败的设备
//				for (PushedNotification pushedNotification : failedNotifications) {
//					System.out.println("失败的token:" + pushedNotification.getDevice().getToken() + ";原因："
//							+ pushedNotification.getException().getMessage());
//				}
//			}
//			pushManager.stopConnection();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	public static void main(String[] args) {
//		IOSNoticeServer send=new IOSNoticeServer();
//		List<String> tokens=new ArrayList<String>();
//		tokens.add("fa5af30830b33abcf4053910cdfebfe50b6d386419a713e444c02fb5fe30a6a2");//错误的
//		tokens.add("fa5af30830b33abcf4053910cdfebfe50b6d386419a713e444c02fb5fe30a6a1");//错误的
//		tokens.add("fa5af30830b33abcf4053910cdfebfe50b6d386419a713e444c02fb5fe30a6ae");//正确的
//		
//		String certificate="/Users/chaowang/Documents/workspace/test/src/main/resources/tM4dVGOTdpzbt9VgwUuloMgs-gzGzoHsz.p12";
//		String password="";
//		String message="{'aps':{'alert':'test'}}";
//		Integer count=1;
//		boolean sendCount=false;
//		send.sendpush(tokens, certificate, password, message, count, sendCount);
//	}
//}
