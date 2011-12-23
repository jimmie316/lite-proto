package com.liteProto;

public class LlpMessage {
	private long   llpMesHandle;
	private String  name;
	
	public LlpMessage(long handle, String name)
	{
		this.llpMesHandle = handle;
		this.name = name;
	}
	
	public LlpMessage(long handle, String name, byte[] buff) throws Exception
	{
		this(handle, name);
		this.decode(buff);
	}
	// destory
	public void destory()
	{
		LlpJavaNative.llpMessageFree(llpMesHandle);	
	}
	
	//  clear
	public void clear()
	{
		LlpJavaNative.llpMessageClr(llpMesHandle);
	}
	
	public void write(String filedStr, int number) throws Exception
	{
		int ret = LlpJavaNative.llpWmesInt32(llpMesHandle, LlpJavaNative.strByte(filedStr), number);
		if(ret == 0)
			throw new Exception("[LlpJavaNative WriteInt32]:  write message \""+name+"\" filed \""+filedStr+"\" number: "+number+" is error.");
	}
	
	public void write(String filedStr, long number) throws Exception
	{
		int ret = LlpJavaNative.llpWmesInt64(llpMesHandle, LlpJavaNative.strByte(filedStr), number);
		if(ret == 0)
			throw new Exception("[LlpJavaNative WriteInt64]:  write message \""+name+"\" filed \""+filedStr+"\" number: "+number+" is error.");
	}
	
	public void write(String filedStr, float number) throws Exception
	{
		int ret = LlpJavaNative.llpWmesFloat32(llpMesHandle, LlpJavaNative.strByte(filedStr), number);
		if(ret == 0)
			throw new Exception("[LlpJavaNative Writefloat32]:  write message \""+name+"\" filed \""+filedStr+"\" number: "+number+" is error.");
	}
	
	public void write(String filedStr, double number) throws Exception
	{
		int ret = LlpJavaNative.llpWmesFloat64(llpMesHandle, LlpJavaNative.strByte(filedStr), number);
		if(ret == 0)
			throw new Exception("[LlpJavaNative Writefloat64]:  write message \""+name+"\" filed \""+filedStr+"\" number: "+number+" is error.");
	}
	
	public void write(String filedStr, String str) throws Exception
	{
		int ret = LlpJavaNative.llpWmesString(llpMesHandle, LlpJavaNative.strByte(filedStr), LlpJavaNative.strByte(str));
		if(ret == 0)
			throw new Exception("[LlpJavaNative WriteString]:  write message \""+name+"\" filed \""+filedStr+"\" str: "+str+" is error.");
	}
	
	public LlpMessage write(String filedStr) throws Exception
	{
		long handle = LlpJavaNative.llpWmesMessage(llpMesHandle, LlpJavaNative.strByte(filedStr));
		if(handle == 0)
			throw new Exception("[LlpJavaNative writeMessage]:  write message \""+name+"\" filed \""+filedStr+"\"is error.");
		
		return new LlpMessage(handle, filedStr);
	}
	
	public int readInt(String filedStr, int alInx)
	{
		return LlpJavaNative.llpRmesInt32(llpMesHandle, LlpJavaNative.strByte(filedStr), alInx);
	}
	public int readInt(String filedStr)
	{
		return readInt(filedStr, 0);
	}
	
	public long readLong(String filedStr, int alInx)
	{
		return LlpJavaNative.llpRmesInt64(llpMesHandle, LlpJavaNative.strByte(filedStr), alInx);
	}
	public long readLong(String filedStr)
	{
		return readLong(filedStr, 0);
	}
	
	public float readFloat(String filedStr, int alInx)
	{
		return LlpJavaNative.llpRmesFloat32(llpMesHandle, LlpJavaNative.strByte(filedStr), alInx);
	}
	public float readFloat(String filedStr)
	{
		return readFloat(filedStr, 0);
	}
	
	public double readDouble(String filedStr, int alInx)
	{
		return LlpJavaNative.llpRmesFloat64(llpMesHandle, LlpJavaNative.strByte(filedStr), alInx);
	}
	public double readDouble(String filedStr)
	{
		return readDouble(filedStr, 0);
	}
	
	public String readString(String filedStr, int alInx)
	{
		byte[] str = LlpJavaNative.llpRmesString(llpMesHandle, LlpJavaNative.strByte(filedStr), alInx);
		
		return new String(str);
	}
	public String readString(String filedStr)
	{
		return readString(filedStr, 0);
	}
	
	public LlpMessage readMessage(String filedStr, int alInx) throws Exception
	{
		long handle = LlpJavaNative.llpRmesMessage(llpMesHandle, LlpJavaNative.strByte(filedStr), alInx);
		if(handle == 0)
			throw new Exception("[LlpJavaNative readMes]:  read message \""+name+"\" is error.");
		
		return new LlpMessage(handle, filedStr);
	}
	public LlpMessage readMessage(String filedStr) throws Exception
	{
		return readMessage(filedStr, 0);
	}
	
	// 编码
	public byte[] encode()
	{
		return LlpJavaNative.llpOutMessage(llpMesHandle);
	}
	
	// 解码
	public void decode(byte[] buff) throws Exception
	{
		this.clear();
		if (LlpJavaNative.llpInMessage(buff, llpMesHandle) == 0)
			throw new Exception("[LlpJavaNative decode]:  decode message \""+name+"\" is error.");
	}
}
