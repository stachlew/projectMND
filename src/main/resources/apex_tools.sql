CREATE PACKAGE APEX_TOOLS AS 

  procedure product_category_ins(pi_name varchar2);
  procedure product_category_upd(pi_id number, pi_name varchar2);
  procedure product_category_del(pi_id number);

  procedure product_ins(pi_name varchar2,pi_kcalories number, pi_price number, pi_product_category_id number);
  procedure product_upd(pi_id number, pi_name varchar2,pi_kcalories number, pi_price number, pi_product_category_id number);
  procedure product_del(pi_id number);

  procedure order_ins(pi_max_cost number,pi_username varchar2);

END APEX_TOOLS;
/

CREATE PACKAGE BODY APEX_TOOLS AS

  procedure product_category_ins(pi_name varchar2) AS
  BEGIN
    insert into PRODUCT_CATEGORY (ID,NAME) values (CAT_PROD_SEQ.nextval,pi_name);
  END product_category_ins;

  procedure product_category_upd(pi_id number, pi_name varchar2) AS
  BEGIN
    update PRODUCT_CATEGORY set name=pi_name where id =pi_id;
  END product_category_upd;

  procedure product_category_del(pi_id number) AS
  BEGIN
    delete PRODUCT_CATEGORY where id = pi_id;
  END product_category_del;

  procedure product_ins(pi_name varchar2,pi_kcalories number, pi_price number, pi_product_category_id number) AS
  BEGIN
    insert into PRODUCT (ID,NAME,KCALORIES,PRICE,PRODUCT_CATEGORY_ID) values (PROD_SEQ.nextval,pi_name,pi_kcalories,pi_price,pi_product_category_id);
  END product_ins;

   procedure product_upd(pi_id number, pi_name varchar2,pi_kcalories number, pi_price number, pi_product_category_id number) AS
  BEGIN
    update PRODUCT set name=pi_name,KCALORIES=pi_kcalories,price=pi_price,PRODUCT_CATEGORY_ID=pi_product_category_id where id =pi_id;
  END product_upd;

  procedure product_del(pi_id number) AS
  BEGIN
    delete PRODUCT where id = pi_id;
  END product_del;

  procedure order_ins(pi_max_cost number,pi_username varchar2)AS 
  v_user_id number;
  BEGIN
  select id into v_user_id from APLICATION_USER where USERNAME = pi_username;
  INSERT INTO purchase_order (
    id,
    max_cost,
    aplication_user_id,
    order_status_id
) VALUES (
    order_seq.nextval,
    pi_max_cost,
    v_user_id,
    1
);
  END order_ins;

END APEX_TOOLS;
/

