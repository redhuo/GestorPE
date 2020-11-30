BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "escuela" (
	"codigo"	TEXT NOT NULL UNIQUE,
	"nombre"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("codigo")
);
CREATE TABLE IF NOT EXISTS "plan_de_estudio" (
	"numero"	INTEGER NOT NULL CHECK("numero" < 10000) UNIQUE,
	"fechaVigencia"	TEXT NOT NULL,
	"escuela"	TEXT NOT NULL,
	PRIMARY KEY("numero"),
	FOREIGN KEY("escuela") REFERENCES "escuela"("codigo") on delete cascade on update cascade
);
CREATE TABLE IF NOT EXISTS "curso" (
	"codigo"	TEXT NOT NULL UNIQUE,
	"nombre"	TEXT NOT NULL,
	"creditos"	INTEGER NOT NULL,
	"horas_lectivas"	INTEGER NOT NULL,
	"escuela"	TEXT NOT NULL,
	FOREIGN KEY("escuela") REFERENCES "escuela"("codigo") on delete cascade on update cascade,
	PRIMARY KEY("codigo")
);
CREATE TABLE IF NOT EXISTS "curso_requisito" (
	"curso"	TEXT NOT NULL,
	"requisitos"	TEXT NOT NULL,
	FOREIGN KEY("curso") REFERENCES "curso"("codigo"),
	FOREIGN KEY("requisitos") REFERENCES "curso"("codigo"),
	PRIMARY KEY("curso","requisitos")
);
CREATE TABLE IF NOT EXISTS "curso_correquisito" (
	"curso"	TEXT NOT NULL,
	"correquisito"	TEXT NOT NULL,
	FOREIGN KEY("correquisito") REFERENCES "curso"("codigo"),
	FOREIGN KEY("curso") REFERENCES "curso"("codigo"),
	PRIMARY KEY("curso","correquisito")
);
CREATE TABLE IF NOT EXISTS "bloque" (
	"plan"	INTEGER NOT NULL,
	"curso"	TEXT NOT NULL,
	"numero"	TEXT NOT NULL,
	FOREIGN KEY("curso") REFERENCES "curso"("codigo"),
	PRIMARY KEY("plan","curso"),
	FOREIGN KEY("plan") REFERENCES "plan_de_estudio"("numero")
);
COMMIT;
