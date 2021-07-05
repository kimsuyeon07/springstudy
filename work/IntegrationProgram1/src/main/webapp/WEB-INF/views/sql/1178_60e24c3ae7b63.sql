DROP SEQUENCE SEARCHBOARD_SEQ;
CREATE SEQUENCE SEARCHBOARD_SEQ START WITH 1 INCREMENT BY 1 NOCYCLE NOCACHE;

DROP TABLE SEARCHBOARD;
CREATE TABLE SEARCHBOARD
(
	NO NUMBER PRIMARY KEY,
	TITLE VARCHAR2(1000),
	CONTENT VARCHAR2(4000),
	REGDATE DATE
);

INSERT INTO SEARCHBOARD VALUES (SEARCHBOARD_SEQ.NEXTVAL, 'SF영화', '뉴욕에서 음악 선생님으로 일하던 ‘조’는 꿈에 그리던 최고의 밴드와 재즈 클럽에서 연주하게 된 그 날, 예기치 못한 사고로 영혼이 되어 ‘태어나기 전 세상’에 떨어진다. 탄생 전 영혼들이 멘토와 함께 자신의 관심사를 발견하면 지구 통행증을 발급하는', SYSDATE);
INSERT INTO SEARCHBOARD VALUES (SEARCHBOARD_SEQ.NEXTVAL, '코미디영화', '강력반에서 좌천되어 신변보호 업무를 떠 맡게 된 이혼 4년 차 자', SYSDATE);
INSERT INTO SEARCHBOARD VALUES (SEARCHBOARD_SEQ.NEXTVAL, 'SF영화', 'UN합동 보안 작전부 아르테미스 대위(밀라 요보비치)는 행방불명된 팀원들을 찾기 위해 나서지만 실종된 그들과 같은 이상 현상으로 거대 몬스터의 세계로 빠진다. 하지만 눈앞에 닥친 강력한 몬스터들의 습격으로 유일한 생존자가 된 그녀는', SYSDATE);
INSERT INTO SEARCHBOARD VALUES (SEARCHBOARD_SEQ.NEXTVAL, '드라마', '누구보다 강한 생활력으로 하루하루 살아온 아동학과 졸업반의 보호종료아동 ‘아영’(김향기) 돈이 필요했던 ‘아영’은 생후 6개월 된 아들 ‘혁’이를 홀로 키우는 워킹맘이자 초보 엄마 ‘영채’(류현경)의 베이비시터가 된다.', SYSDATE);
INSERT INTO SEARCHBOARD VALUES (SEARCHBOARD_SEQ.NEXTVAL, '드라마', '자상한 아빠, 귀여운 햄스터와 행복한 나날을 보내던 소녀는 새로운 가족 ‘제니’의 등장으로 평온하던 일상에 변화가 찾아오고 급기야 ‘비고’를 잃을 위기에 처한다. 그러던 어느 날 밤, 우연히 꿈속 세상을 발견한 ‘미나’는', SYSDATE);
INSERT INTO SEARCHBOARD VALUES (SEARCHBOARD_SEQ.NEXTVAL, '코미디영화', '희대의 폭파 전문 은행털이범 ‘톰’. 어떠한 증거도 남기지 않는 뛰어난 범행으로 8년간 FBI의 추적을 따돌린 그는 새로운 인생을 시작하기 위해 자수를 결심한다. 그러나 돈을 노리는', SYSDATE);
INSERT INTO SEARCHBOARD VALUES (SEARCHBOARD_SEQ.NEXTVAL, 'SF영화', '뛰어난 손재주를 가진 ‘엘프’들은 인간을 도우며 함께 지내왔지만 어느 날 욕심 많은 인간에게 큰 상처를 입고 지하 세계에 숨어 지낸다. 특별한 재주가 없던 꼬마 요정 ‘엘피’는 손재주를 가르쳐 줄 인간을 찾아 친구 ‘벅’, ‘킵’과 함께 무시무시한 소문으로', SYSDATE);
COMMIT;