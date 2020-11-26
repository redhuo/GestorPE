create table escuela(
    codigo text primary key,
    nombre text not null
);

create table plan_de_estudio(
	numero int primary key,
	fechaVigencia text not null
);

create table curso(
	codigo text primary key,
	nombre text not null,
	creditos int not null,
	hora_lectivas int not null
);

create table curso_requisito(
	codigo_curso text not null,
	codigo_requisito text not null,
	foreign key(codigo_curso) references curso(codigo),
	foreign key(codigo_requisito) references curso(codigo)	
);

create table curso_correquisito(
	codigo_curso text not null,
	codigo_correquisito text not null,
	foreign key(codigo_curso) references curso(codigo),
	foreign key(codigo_correquisito) references curso(codigo)	
);

create table escuela_curso(
	codigo_escuela text not null,
	codigo_curso text not null,
	foreign key(codigo_escuela) references escuela(codigo),
	foreign key(codigo_curso) references curso(codigo)
);

create table escuela_plandeestudio(
	numero_plan int not null,
	codigo_escuela text bot null,
	foreign key(numero_plan) references plan_de_estudio(numero),
	foreign key(codigo_escuela) references escuela(codigo)
);

create table plandeestudio_curso(
	numero int not null,
	codigo text not null,
	foreign key(numero) references plan_de_estudio(numero),
	foreign key(codigo) references curso(codigo)
);

create table bloque (
	numero_plan int not null,
	codigo_curso text not null,
	numero_bloque int not null,
	foreign key(numero_plan) references plan_de_estudio(numero),
	foreign key(codigo_curso) references curso(codigo)
);