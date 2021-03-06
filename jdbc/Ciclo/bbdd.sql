--------------------------------------------------------
-- Archivo creado  - mi�rcoles-febrero-15-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table CICLO
--------------------------------------------------------

  CREATE TABLE "VS2DAWR5"."CICLO" 
   (	"IDCICLO" NUMBER(3,0), 
	"NOMBRE" VARCHAR2(30 BYTE), 
	"GRADO" VARCHAR2(15 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table IES
--------------------------------------------------------

  CREATE TABLE "VS2DAWR5"."IES" 
   (	"IDIES" NUMBER(3,0), 
	"NOMBRE" VARCHAR2(25 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table OFERTAEDUCATIVA
--------------------------------------------------------

  CREATE TABLE "VS2DAWR5"."OFERTAEDUCATIVA" 
   (	"IDIES" NUMBER(3,0), 
	"IDCICLO" NUMBER(3,0), 
	"TURNO" NUMBER, 
	"PLAZAS" NUMBER
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TURNO
--------------------------------------------------------

  CREATE TABLE "VS2DAWR5"."TURNO" 
   (	"IDTURNO" NUMBER, 
	"DESCRIPCION" VARCHAR2(15 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into VS2DAWR5.CICLO
SET DEFINE OFF;
Insert into VS2DAWR5.CICLO (IDCICLO,NOMBRE,GRADO) values ('1','DESARR.APLI.IN','SUPERIOR');
Insert into VS2DAWR5.CICLO (IDCICLO,NOMBRE,GRADO) values ('2','ADM.SIST.INFORM','SUPERIOR');
Insert into VS2DAWR5.CICLO (IDCICLO,NOMBRE,GRADO) values ('3','HOSTELERIA','SUPERIOR');
Insert into VS2DAWR5.CICLO (IDCICLO,NOMBRE,GRADO) values ('4','PREPARADOR FISICO','SUPERIOR');
Insert into VS2DAWR5.CICLO (IDCICLO,NOMBRE,GRADO) values ('5','SECRETARIADO','MEDIO');
Insert into VS2DAWR5.CICLO (IDCICLO,NOMBRE,GRADO) values ('6','ADMINISTRACION Y FINANZAS','SUPERIOR');
Insert into VS2DAWR5.CICLO (IDCICLO,NOMBRE,GRADO) values ('7','CONSTRUCCION Y OBRA CIVIL','SUPERIOR');
Insert into VS2DAWR5.CICLO (IDCICLO,NOMBRE,GRADO) values ('8','IMAGEN Y SONIDO','SUPERIOR');
REM INSERTING into VS2DAWR5.IES
SET DEFINE OFF;
Insert into VS2DAWR5.IES (IDIES,NOMBRE) values ('1','AZARQUIEL');
Insert into VS2DAWR5.IES (IDIES,NOMBRE) values ('2','C.E.I.');
Insert into VS2DAWR5.IES (IDIES,NOMBRE) values ('3','JUANELO TURRIANO');
Insert into VS2DAWR5.IES (IDIES,NOMBRE) values ('4','SEFARAD');
REM INSERTING into VS2DAWR5.OFERTAEDUCATIVA
SET DEFINE OFF;
Insert into VS2DAWR5.OFERTAEDUCATIVA (IDIES,IDCICLO,TURNO,PLAZAS) values ('1','1','1','20');
Insert into VS2DAWR5.OFERTAEDUCATIVA (IDIES,IDCICLO,TURNO,PLAZAS) values ('1','2','1','3');
Insert into VS2DAWR5.OFERTAEDUCATIVA (IDIES,IDCICLO,TURNO,PLAZAS) values ('1','2','2','4');
Insert into VS2DAWR5.OFERTAEDUCATIVA (IDIES,IDCICLO,TURNO,PLAZAS) values ('1','6','1','1');
Insert into VS2DAWR5.OFERTAEDUCATIVA (IDIES,IDCICLO,TURNO,PLAZAS) values ('1','7','1','6');
Insert into VS2DAWR5.OFERTAEDUCATIVA (IDIES,IDCICLO,TURNO,PLAZAS) values ('2','3','1','1');
Insert into VS2DAWR5.OFERTAEDUCATIVA (IDIES,IDCICLO,TURNO,PLAZAS) values ('2','5','1','1');
Insert into VS2DAWR5.OFERTAEDUCATIVA (IDIES,IDCICLO,TURNO,PLAZAS) values ('2','6','1','1');
Insert into VS2DAWR5.OFERTAEDUCATIVA (IDIES,IDCICLO,TURNO,PLAZAS) values ('3','8','1','2');
Insert into VS2DAWR5.OFERTAEDUCATIVA (IDIES,IDCICLO,TURNO,PLAZAS) values ('3','2','1','8');
Insert into VS2DAWR5.OFERTAEDUCATIVA (IDIES,IDCICLO,TURNO,PLAZAS) values ('4','4','1','4');
REM INSERTING into VS2DAWR5.TURNO
SET DEFINE OFF;
Insert into VS2DAWR5.TURNO (IDTURNO,DESCRIPCION) values ('1','DIURNO');
Insert into VS2DAWR5.TURNO (IDTURNO,DESCRIPCION) values ('2','VESPERTINO');
--------------------------------------------------------
--  DDL for Index PK_CICLO
--------------------------------------------------------

  CREATE UNIQUE INDEX "VS2DAWR5"."PK_CICLO" ON "VS2DAWR5"."CICLO" ("IDCICLO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_IES
--------------------------------------------------------

  CREATE UNIQUE INDEX "VS2DAWR5"."PK_IES" ON "VS2DAWR5"."IES" ("IDIES") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_OFERTAEDUCATIVA
--------------------------------------------------------

  CREATE UNIQUE INDEX "VS2DAWR5"."PK_OFERTAEDUCATIVA" ON "VS2DAWR5"."OFERTAEDUCATIVA" ("IDIES", "IDCICLO", "TURNO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_TURNO
--------------------------------------------------------

  CREATE UNIQUE INDEX "VS2DAWR5"."PK_TURNO" ON "VS2DAWR5"."TURNO" ("IDTURNO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table CICLO
--------------------------------------------------------

  ALTER TABLE "VS2DAWR5"."CICLO" ADD CONSTRAINT "CK_GRADOCICLO" CHECK ( GRADO IN ('MEDIO','SUPERIOR')) ENABLE;
 
  ALTER TABLE "VS2DAWR5"."CICLO" ADD CONSTRAINT "PK_CICLO" PRIMARY KEY ("IDCICLO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table IES
--------------------------------------------------------

  ALTER TABLE "VS2DAWR5"."IES" ADD CONSTRAINT "PK_IES" PRIMARY KEY ("IDIES")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table OFERTAEDUCATIVA
--------------------------------------------------------

  ALTER TABLE "VS2DAWR5"."OFERTAEDUCATIVA" ADD CONSTRAINT "PK_OFERTAEDUCATIVA" PRIMARY KEY ("IDIES", "IDCICLO", "TURNO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table TURNO
--------------------------------------------------------

  ALTER TABLE "VS2DAWR5"."TURNO" ADD CONSTRAINT "PK_TURNO" PRIMARY KEY ("IDTURNO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table OFERTAEDUCATIVA
--------------------------------------------------------

  ALTER TABLE "VS2DAWR5"."OFERTAEDUCATIVA" ADD CONSTRAINT "FK_OFERTAEDUCATIVA_CICLO" FOREIGN KEY ("IDCICLO")
	  REFERENCES "VS2DAWR5"."CICLO" ("IDCICLO") ENABLE;
 
  ALTER TABLE "VS2DAWR5"."OFERTAEDUCATIVA" ADD CONSTRAINT "FK_OFERTAEDUCATIVA_IES" FOREIGN KEY ("IDIES")
	  REFERENCES "VS2DAWR5"."IES" ("IDIES") ENABLE;
 
  ALTER TABLE "VS2DAWR5"."OFERTAEDUCATIVA" ADD CONSTRAINT "FK__OFERTAEDUCATIVA_TURNO" FOREIGN KEY ("TURNO")
	  REFERENCES "VS2DAWR5"."TURNO" ("IDTURNO") ENABLE;
