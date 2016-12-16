# spring-boot-jms

解决了在spring boot同时使用topic和queue消息的生产及消费

局限：
	当destinationName内包含有topic字符时，使用订阅模式，否则使用点对点消息模式
