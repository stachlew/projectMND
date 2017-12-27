INSERT INTO order_status (    id,    description) VALUES (    order_status_seq.nextval,    'NOWY');
INSERT INTO order_status (    id,    description) VALUES (    order_status_seq.nextval,    'W TRAKCIE');
INSERT INTO order_status (    id,    description) VALUES (    order_status_seq.nextval,    'PO ZMIANIE');
INSERT INTO order_status (    id,    description) VALUES (    order_status_seq.nextval,    'GOTOWY');

commit;