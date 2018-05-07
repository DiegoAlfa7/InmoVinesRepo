create table cargos
(
  id_cargo bigint unsigned auto_increment
    primary key,
  nombre   varchar(50) not null
)
  charset = latin1;

create table agentes
(
  id            bigint auto_increment
    primary key,
  nombre        varchar(50)                     not null,
  apellidos     varchar(150)                    null,
  mail          varchar(50)                     null,
  tlf           varchar(20)                     null,
  twitter       varchar(50)                     null,
  facebook      varchar(50)                     null,
  linkedin      varchar(50)                     null,
  instagram     varchar(50)                     null,
  foto          varchar(150)                    null,
  eslogan       varchar(500)                    null,
  codigo_agente varchar(10)                     null,
  activacion    tinyint(1)                      null,
  permisos      smallint(6)                     null,
  password      varchar(100)                    null,
  id_cargo      bigint(10) unsigned default '0' not null,
  constraint FK_cargos
  foreign key (id_cargo) references cargos (id_cargo)
    on update cascade
)
  collate = utf8_spanish_ci;

create index FK_cargos
  on agentes (id_cargo);

create table clientes
(
  id                  bigint auto_increment
    primary key,
  nombre              varchar(30) charset utf8  null,
  apellidos           varchar(100) charset utf8 null,
  mail                varchar(100) charset utf8 null,
  inquilino           tinyint(1)                not null,
  arrendatario        tinyint(1)                not null,
  comprador           tinyint(1)                not null,
  vendedor            tinyint(1)                not null,
  telefono            varchar(15) charset utf8  null,
  telefono_1          varchar(15) charset utf8  null,
  mail_1              varchar(100) charset utf8 null,
  comentarios         text                      null,
  id_agente           bigint                    not null,
  fecha_entrada       date                      null,
  presupuesto_min     bigint unsigned           null,
  presupuesto_max     bigint unsigned           null,
  id_agente_entrada   bigint                    null,
  id_inmueble_interes bigint                    null,
  dni                 varchar(30)               null,
  canal_entrada       smallint(5) unsigned      null,
  nacionalidad        varchar(50)               null,
  edad                int                       null,
  constraint FK_agente_de_cliente
  foreign key (id_agente) references agentes (id)
    on update cascade,
  constraint FK_agente_de_entrada
  foreign key (id_agente_entrada) references agentes (id)
    on update cascade
)
  collate = utf8_spanish_ci;

create index FK_inmueble_interes
  on clientes (id_inmueble_interes);

create index id_agente
  on clientes (id_agente);

create index id_agente_entrada
  on clientes (id_agente_entrada);

create table comunidades
(
  ID        bigint auto_increment
    primary key,
  slug      varchar(50)  not null,
  comunidad varchar(255) not null,
  constraint slug
  unique (slug),
  constraint IDX_cominidad
  unique (comunidad)
)
  collate = utf8_spanish_ci;

create table municipios
(
  ID           bigint auto_increment
    primary key,
  id_provincia bigint       not null,
  municipio    varchar(255) not null,
  slug         varchar(255) not null,
  latitud      double       null,
  longitud     double       null,
  constraint IDX_municipio
  unique (id_provincia, municipio),
  constraint slug
  unique (slug)
)
  collate = utf8_spanish_ci;

create table provincias
(
  ID           bigint auto_increment
    primary key,
  slug         varchar(50)         not null,
  provincia    varchar(255)        not null,
  comunidad_id bigint(10)          not null,
  capital_id   bigint default '-1' not null,
  constraint IDX_provincia
  unique (provincia),
  constraint provincias_comunidades_ID_FK
  foreign key (comunidad_id) references comunidades (ID),
  constraint provincias_municipios_capital_FK
  foreign key (capital_id) references municipios (ID)
)
  collate = utf8_spanish_ci;

create table intereses
(
  id           bigint auto_increment
    primary key,
  habitaciones int    not null,
  baños        int    not null,
  m2           int    not null,
  precioMax    int    not null,
  id_provincia bigint not null,
  id_municipio bigint not null,
  id_cliente   bigint not null,
  constraint FK_provincia
  foreign key (id_provincia) references provincias (ID)
    on update cascade,
  constraint FK_municipio
  foreign key (id_municipio) references municipios (ID)
    on update cascade,
  constraint FK_cliente
  foreign key (id_cliente) references clientes (id)
    on update cascade
);

create index id_cliente
  on intereses (id_cliente);

create index id_municipio
  on intereses (id_municipio);

create index id_provincia
  on intereses (id_provincia);

alter table municipios
  add constraint municipios_provincias_ID_FK
foreign key (id_provincia) references provincias (ID);

create index FK_provincias
  on provincias (comunidad_id);

create index provincias_municipios_capital_FK
  on provincias (capital_id);

create table zonas
(
  id           bigint auto_increment
    primary key,
  nombre       varchar(100) charset utf8 null,
  id_municipio bigint                    not null,
  activa       tinyint(1)                not null,
  nombre_admin varchar(100)              null,
  descripcion  text                      null,
  constraint FK_municip
  foreign key (id_municipio) references municipios (ID)
    on update cascade
)
  collate = utf8_spanish_ci;

create table inmuebles
(
  ID                                 bigint auto_increment
    primary key,
  id_cliente_propietario             bigint                 null,
  id_agente                          bigint                 null,
  referencia_catastral               varchar(100)           null,
  referencia                         varchar(30)            null,
  tipo                               int(4)                 not null
  comment 'Compra (0), Alquiler (1), AoC (2)',
  descripcion                        varchar(1500)          null,
  texto_reclamo                      varchar(200)           null,
  gastos_comunidad                   int                    null,
  altura_edificio                    smallint(5) unsigned   null,
  id_comunidad                       bigint                 not null,
  id_provincia                       bigint                 null,
  id_municipios                      bigint                 null,
  id_zona                            bigint                 null,
  cp                                 int(10)                null,
  latitud                            double                 null,
  longitud                           double                 null,
  direccion_tipo_via                 varchar(20)            null,
  direccion_calle                    varchar(100)           null,
  direccion_numero                   varchar(20)            null,
  direccion_piso                     varchar(20)            null,
  direccion_letra                    varchar(20)            null,
  direccion_escalera                 varchar(20)            null,
  precio_compra                      double                 null,
  precio_alquiler                    double                 null,
  precio_traspaso                    double                 null,
  precio_alquiler_opcion_compra      double                 null,
  n_habitaciones                     smallint(5) unsigned   null,
  n_banos                            smallint(5) unsigned   null,
  n_aseos                            smallint(5) unsigned   null,
  m2_utiles                          double                 null,
  m2_construidos                     float                  null,
  m2_terreno                         double                 null,
  estado_conservacion                int(1)                 not null
  comment '[0 - 9] (worst - best)',
  visible                            tinyint(1) default '0' null,
  zona_deportiva                     tinyint(1) default '0' null,
  amueblado                          tinyint(1) default '0' null,
  garaje                             tinyint(1) default '0' null,
  calefaccion                        tinyint(1) default '0' null,
  aire_acondicionado                 tinyint(1) default '0' null,
  piscina                            tinyint(1) default '0' null,
  jardin                             tinyint(1) default '0' null,
  trastero                           tinyint(1) default '0' null,
  ascensor                           tinyint(1) default '0' null,
  terraza                            tinyint(1) default '0' null,
  piso_banco                         tinyint(1) default '0' null,
  vpo                                tinyint(1) default '0' null,
  reservado                          tinyint(1) default '0' null,
  eficiencia_energetica_tipo         varchar(1)             null,
  eficiencia_energetica_entramite_01 tinyint(1)             not null,
  eficiencia_energetica_fecvalid     date                   null,
  eficiencia_energetica_emisiones    float                  null,
  orientacion_solar                  varchar(6)             null,
  suelos                             varchar(20)            null,
  carpinteria_exterior               varchar(20)            null,
  carpinteria_interior               varchar(20)            null,
  constraint FK_propietario
  foreign key (id_cliente_propietario) references clientes (id)
    on update cascade,
  constraint FK_agente
  foreign key (id_agente) references agentes (id)
    on update cascade,
  constraint FK_comunidades
  foreign key (id_comunidad) references comunidades (ID)
    on update cascade,
  constraint FK_provincias
  foreign key (id_provincia) references provincias (ID)
    on update cascade,
  constraint FK_municipios
  foreign key (id_municipios) references municipios (ID)
    on update cascade,
  constraint FK_zona
  foreign key (id_zona) references zonas (id)
    on update cascade
)
  collate = utf8_spanish_ci;

alter table clientes
  add constraint FK_inmueble_interes
foreign key (id_inmueble_interes) references inmuebles (ID)
  on update cascade;

create index FK_comunidades
  on inmuebles (id_comunidad);

create index id_agente
  on inmuebles (id_agente);

create index id_cliente_propietario
  on inmuebles (id_cliente_propietario);

create index id_municipios
  on inmuebles (id_municipios);

create index id_provincia
  on inmuebles (id_provincia);

create index id_zona
  on inmuebles (id_zona);

create index id_municipio
  on zonas (id_municipio);


