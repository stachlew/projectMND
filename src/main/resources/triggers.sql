CREATE OR REPLACE TRIGGER ORDER_UPDATE
BEFORE UPDATE ON PURCHASE_ORDER
FOR EACH ROW
BEGIN

if :OLD.ORDER_STATUS_ID=4  tHEN
  :NEW.ORDER_STATUS_ID:=3;
  END IF;

END;

/

CREATE OR REPLACE TRIGGER REST_INS_UPD
BEFORE INSERT OR UPDATE OR DELETE ON RESTRICTIONS
FOR EACH ROW
declare
v_row purchase_order%rowtype;
BEGIN
IF INSERTING THEN
:NEW.ID := RESTRICTION_SEQ.nextval;
end if;
 begin
 select * into v_row from purchase_order where ID = :NEW.ORDER_ID;
 exception
 when others then
 select * into v_row from purchase_order where ID = :OLD.ORDER_ID;
 end;
 if v_row.ORDER_STATUS_ID = 4 then
  update purchase_order set ORDER_STATUS_ID=3 where ID = v_row.ID;
  end if;
END;