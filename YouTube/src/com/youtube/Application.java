package com.youtube;

import java.util.Scanner;

import com.youtube.controller.YouTubeController;
import com.youtube.model.vo.Category;
import com.youtube.model.vo.Channel;
import com.youtube.model.vo.Member;
import com.youtube.model.vo.Video;

public class Application {
	
	private Scanner sc = new Scanner(System.in);
	private YouTubeController yc = new YouTubeController();

	public static void main(String[] args) {
		Application app = new Application();
//		app.register();
//		app.login();
//		app.addChannel();
		app.addVideo();
	}
	
	public void register() {
		// 회원가입	
		System.out.print("아이디 입력 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 입력 : ");
		String password = sc.nextLine();
		System.out.print("닉네입 입력 : ");
		String nickname = sc.nextLine();
		
		Member member = new Member();
		member.setMemberId(id);
		member.setMemberPassword(password);
		member.setMemberNickname(nickname);
		
		if(yc.register(member)) {
			System.out.println("회원가입에 성공하셨습니다.");
		} else {
			System.out.println("회원가입에 실패하셨습니다.");
		}
	}
	
	public void login() {
		// 로그인
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String password = sc.nextLine();
		
		if(yc.login(id, password) != null) {
			System.out.println(yc.login(id, password).getMemberNickname() + "님 환영합니다.");
		} else {
			System.out.println("누구세요?");
		}
	}
	
	public void addChannel() {
		yc.login("user1", "1234");
		System.out.print("채널명 : ");
		String title = sc.nextLine();
		Channel channel = new Channel();
		channel.setChannelName(title);
		if (yc.addChannel(channel)) {
			System.out.println("채널이 추가되었습니다.");
		} else {
			System.out.println("채널 추가 실페");
		}
	}
	
	public void deleteChannel() {
		yc.login("user1", "1234");
		if(yc.deleteChannel()) {
			System.out.println("채널 삭제 완료!");
		} else {
			System.out.println("채널 삭제 실패 ㅠㅠ");
		}
	}
		
	
	// 비디오 추가
	public void addVideo() {
		categoryList();
		yc.login("user1", "1234");
		System.out.print("비디오 제목 : ");
		String title = sc.nextLine();
		System.out.print("비디오 URL : ");
		String url = sc.nextLine();
		System.out.print("비디오 썸네일 : ");
		String image = sc.nextLine();
		for(Category category : yc.categoryList()) {
			System.out.println(category);
		}
		System.out.print("카테고리 입력 : ");
		int categoryNo = Integer.parseInt(sc.nextLine());
		Video video = new Video();
		video.setVideoTitle(title);
		video.setVideoUrl(url);
		video.setVideoPhoto(image);
		Category category = new Category();
		category.setCategoryCode(categoryNo);
		video.setCategory(category);

		if(yc.addVideo(video)) {
			System.out.println("비디오 추가 성공!");
		} else System.out.println("비디오 추가 실패");
	}
	
	public void categoryList() {
		for(Category category : yc.categoryList()) {
			System.out.println(category);
		}
	}

}
