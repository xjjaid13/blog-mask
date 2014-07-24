package com.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;

import com.exception.FunctionException;

/**  
 * 文件处理类
 * <p>2014年7月23日下午3:37:36 xijiajia</p>
 */
public class FileHandle {

	//log
	private static Logger log = Logger.getLogger(FileHandle.class);
	
	/**
	 * 创建文件夹，如果文件夹存在，则创建失败
	 * @param filePath 创建的文件夹路径
	 * @return
	 */
	public static boolean createPath(String filePath){
		File file = new File(filePath);
		boolean b = false;
		if(!file.exists()){
			b = file.mkdirs();
		}
		return b;
	}
	
	/**
	 * 拷贝文件
	 * @param sourceFile 源文件
	 * @param desFile 目的文件
	 */
	public static void copyFile(File sourceFile,File desFile){
		FileInputStream input = null;
		BufferedInputStream inBuff = null;
		FileOutputStream output = null;
		BufferedOutputStream outBuff = null;
		try{
			input = new FileInputStream(sourceFile);
		    inBuff = new BufferedInputStream(input);
	        output = new FileOutputStream(desFile);
	        outBuff = new BufferedOutputStream(output);
	        byte[] b = new byte[1024 * 5];
	        int len;
	        while ((len =inBuff.read(b)) != -1) {
	            outBuff.write(b, 0, len);
	        }
	        outBuff.flush();
		}catch(Exception e){
			throw new FunctionException("拷贝文件发生异常",e);
		}finally{
			//关闭流 
	        try {
	        	if(inBuff != null){
	        		inBuff.close();
	        	}
	        	if(outBuff != null){
	        		outBuff.close();
	        	}
				if(output != null){
					output.close();
				}
		        if(input != null){
		        	input.close();
		        }
			} catch (IOException e) {
				throw new FunctionException("拷贝文件关闭流发生异常",e);
			}
		}
	}
	
	/**
	 * 拷贝文件夹
	 * @param sourceDir 源文件夹
	 * @param targetDir 目标文件夹
	 */
	public static void copyDirectiory(String sourceDir, String targetDir){
		try{
			File target = new File(targetDir);
			if(!target.exists()){
				target.mkdirs();
			}
			File[] file = (new File(sourceDir)).listFiles();
			for (int i = 0; i < file.length; i++) {
			    if (file[i].isFile()) {
			        File sourceFile=file[i];
			        File targetFile=new File(target.getAbsolutePath()+File.separator+file[i].getName());
			        copyFile(sourceFile,targetFile);
			    }
			    if (file[i].isDirectory()) {
			        String dir1=sourceDir + "/" + file[i].getName();
			        String dir2=targetDir + "/"+ file[i].getName();
			        copyDirectiory(dir1, dir2);
			    }
			}
		}catch(Exception e){
			throw new FunctionException("拷贝文件夹异常",e);
		}
}
	
	/**
	 * 删除文件
	 * @param file 文件名
	 */
	public static void delPath(File file){
		if(file.exists()){
		    if(file.isFile()){
		        file.delete();
		    }else if(file.isDirectory()){
		        File files[] = file.listFiles();
		        for(int i=0;i<files.length;i++){
		        	delPath(files[i]);
		        }
		    }
		    file.delete();
		}else{
		    log.debug("删除文件不存在");
		} 
	}
	
	/**
	 * 写内容到文件
	 * @param path 写入路径
	 * @param content 文件内容
	 * @return
	 */
	public static boolean write(String path, String content) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		boolean result = false;
		try {
			File f = new File(path);
			if (!f.exists() && f.createNewFile()) {
				result = true;
			}
			fos = new FileOutputStream(path);
            osw = new OutputStreamWriter(fos,"UTF-8");
            osw.write(content);
            osw.flush();
            return result;
		} catch (Exception e) {
			throw new FunctionException("写入文件异常",e);
		} finally{
			try{
				if(fos != null){
					fos.close();
				}
				if(osw != null){
					osw.close();
				}
			}catch(Exception e){
				throw new FunctionException("写入文件关闭连接异常",e);
			}
		}
	}
	
	/**
	 * 写内容到文件，是否覆盖
	 * @param path 路径
	 * @param content 内容
	 * @param isCover 是否覆盖 true覆盖 false不覆盖
	 * @return
	 */
	public static boolean write(String path,String content,boolean isCover) {
		boolean result = false;
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			File f = new File(path);
			if(f.exists()){
				if(!isCover){
					return true;
				}
			}else{
				f.createNewFile();
			}
			fos = new FileOutputStream(path);
            osw = new OutputStreamWriter(fos, "UTF-8");
            osw.write(content);
            osw.flush();
            return result;
		} catch (Exception e) {
			throw new FunctionException("写入文件异常",e);
		} finally{
			try{
				if(fos != null){
					fos.close();
				}
				if(osw != null){
					osw.close();
				}
			}catch(Exception e){
				throw new FunctionException("写入文件关闭连接异常",e);
			}
		}
	}
	
	/**
	 * 读取文件
	 * @param filePath 文件路径
	 * @param encode 编码
	 * @return String
	 * */
	public static String readFile(String filePath,String encode){
		File file = null;
		InputStream in = null;
		BufferedReader rb = null;
		InputStreamReader inReader = null;
		StringBuffer strBuf = null;
		try{
			strBuf = new StringBuffer();
			file = new File(filePath);
			in = new FileInputStream(file);
			inReader = new InputStreamReader(in,encode);
			rb = new BufferedReader(inReader);
			int tempbyte;
			while((tempbyte = rb.read()) != -1){
				strBuf.append((char)tempbyte);
			}
			return strBuf.toString();
		}catch(Exception e){
			throw new FunctionException("读取文件异常",e);
		}finally{
			try{
				if(in != null){
					in.close();
				}
				if(inReader != null){
					inReader.close();
				}
				if(rb != null){
					rb.close();
				}
			}catch(Exception e){
				throw new FunctionException("读取文件释放连接异常",e);
			}
		}
	}
	
	/**
	 * 读取文件，默认utf-8
	 * @param filePath 文件路径
	 * */
	public static String readFile(String filePath){
		File file = null;
		InputStream in = null;
		BufferedReader rb = null;
		StringBuffer strBuf = null;
		InputStreamReader inReader = null;
		try{
			strBuf = new StringBuffer();
			file = new File(filePath);
			in = new FileInputStream(file);
			inReader = new InputStreamReader(in,"UTF-8");
			rb = new BufferedReader(inReader);
			int tempbyte;
			while((tempbyte = rb.read()) != -1){
				strBuf.append((char)tempbyte);
			}
			return strBuf.toString();
		}catch(IOException e){
			throw new FunctionException("读取文件异常",e);
		}finally{
			try{
				if(in != null){
					in.close();
				}
				if(inReader != null){
					inReader.close();
				}
				if(rb != null){
					rb.close();
				}
			}catch(Exception e){
				throw new FunctionException("读取文件释放连接异常",e);
			}
		}
	}
	
	/**
	 * 文件重命名
	 * */
	public static void rename(String file,String file2){
		new File(file).renameTo(new File(file2));
	}
	
	/**
	 * 写入文件
	 * */
	@SuppressWarnings("resource")
	public boolean writeFile(String filePath,String content){
		FileWriter fw;
		boolean b = false;
		try {
			fw = new FileWriter(filePath);
			fw.write(content,0,content.length());  
			fw.flush();
			b = true;
		} catch (Exception e) {
			throw new FunctionException("写入文件异常",e);
		}  
		return b;
	}
	
}
