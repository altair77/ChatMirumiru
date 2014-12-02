package com.altair.chatMirumiru;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ChatMirumiruFile {

	private File parentDir = null;
	private File ioFile = null;
	private PrintWriter pw = null;

	public ChatMirumiruFile(File parentDir) {
		this.parentDir = parentDir;
	}

	public ChatMirumiruFile(File parentDir, String fileName) {
		this.parentDir = parentDir;
		ioFile = new File(parentDir, fileName);
		try {
			ioFile.createNewFile();
		} catch (IOException e) {
			ChatMirumiruCore.log.error("Failed to prepare the related file. (" + ioFile.getAbsolutePath() + ")");
		}
	}

	/**
	 * 新たにディレクトリを作成する
	 * すでに存在している場合は何もしない
	 * @param dirName ディレクトリ名
	 * @return 新たに作成したディレクトリ
	 */
	public File makeDir(String dirName){
		File newDir = new File(parentDir, dirName);
		if(newDir.exists())
			return newDir;
		newDir.mkdir();
		return newDir;
	}

	public ArrayList<String> readFile(){
		if(ioFile == null)
			return null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(ioFile));
			String line;
			while((line = br.readLine()) != null)
				list.add(line);
			br.close();
		} catch (FileNotFoundException e) {
			ChatMirumiruCore.log.error("Not found the related file. (" + ioFile.getAbsolutePath() + ")");
		} catch (IOException e) {
			ChatMirumiruCore.log.error("Failed to read the related file. (" + ioFile.getAbsolutePath() + ")");
		}
		return list;
	}

	public void writeLine(String line){
		if(pw == null)
			return;
		pw.println(line);
	}

	public void openWriteFile(){
		if(ioFile == null)
			return;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(ioFile)));
		} catch (IOException e) {
			ChatMirumiruCore.log.error("Failed to write the related file. (" + ioFile.getAbsolutePath() + ")");
		}
	}

	public void closeWriteFile(){
		pw.close();
		pw = null;
	}

	public void flushFile(){
		if(pw == null)
			return;
		pw.flush();
	}
}
