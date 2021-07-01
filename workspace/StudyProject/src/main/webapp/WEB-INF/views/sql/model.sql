-- 기존에 있는 테이블이라면, 삭제
DROP TABLE REPLY;
DROP TABLE BOARD;
DROP TABLE MEMBER;

-- 회원 테이블 생성
CREATE TABLE MEMBER 
(
	NO NUMBER PRIMARY KEY,
	ID VARCHAR2(32) NOT NULL UNIQUE,
	PW VARCHAR2(50) NOT NULL,
	NAME VARCHAR2(32),
	PHONE VARCHAR2(100),
	EMAIL VARCHAR2(100),
	ADDRESS VARCHAR2(100),
	POSTDATE DATE,
	STATE NUMBER  -- (정상:0, 탈퇴:-1)
);

-- 회원 시퀀스 생성
DROP SEQUENCE MEMBER_SEQ;
CREATE SEQUENCE MEMBER_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE NOCACHE;






-- 갤러리게시판 테이블 생성
CREATE TABLE GALLERY_BOARD
(
	NO NUMBER PRIMARY KEY,
	WRITER VARCHAR2(32) REFERNCES MEMBER (ID),
	TITLE VARCHAR2(50) NOT NULL,
	CONTENT VARCHAR2(4000),
	POSTDATE DATE,
	IP VARCHAR2(100),
	HIT NUMBER,
	IMGNAME VARCHAR2(100)
);






