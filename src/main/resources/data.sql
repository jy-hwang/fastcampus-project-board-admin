-- 테스트계정 5개
-- TODO : 테스트용 이지만 비밀번호가 노출된 데이터 세팅. 개선하는 것이 좋을지 고민해봐야함. -> 시큐리티 적용하려면 어떻게 해야할지 조금 고민해봐야할 문제.
insert into user_account(user_id, user_password,role_types, nickname, email, memo, created_at, created_by, modified_at, modified_by) values
  ('admin1','{noop}asdasd1234','ADMIN','admin1','admin1@abc.com','I am your father', now(), 'admin1',now(),'admin1')
, ('admin2','{noop}asdasd1234','MANAGER','admin2','admin2@abc.com','I am your father', now(), 'admin2',now(),'admin2')
, ('admin3','{noop}asdasd1234','DEVELOPER','admin3','admin3@abc.com','I am your father', now(), 'admin3',now(),'admin3')
, ('admin4','{noop}asdasd1234','USER','admin4','admin4@abc.com','I am your father', now(), 'admin4',now(),'admin4')
;
