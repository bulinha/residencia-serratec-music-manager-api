CREATE TABLE artista (
	art_cd_id integer NOT NULL ,
	art_tx_nome varchar(100) NOT NULL,
	art_tx_tipo varchar(1) NOT NULL,
	PRIMARY KEY(art_cd_id)
);

--TODO data_lancamento
CREATE TABLE album (
	alb_cd_id integer NOT NULL ,
	alb_tx_titulo varchar(200) NOT NULL,
	art_cd_id integer NOT NULL,
	PRIMARY KEY(alb_cd_id),
	FOREIGN KEY (art_cd_id) REFERENCES artista(art_cd_id)
);

CREATE TABLE album_capa (
	alb_cd_id integer NOT NULL,
	alc_tx_media_type varchar(200) NOT NULL,
	alc_bl_capa blob NOT NULL,
	PRIMARY key(alb_cd_id),
	FOREIGN KEY (alb_cd_id) REFERENCES album(alb_cd_id)
);

CREATE TABLE musica (
  mus_cd_id integer NOT NULL ,
  mus_tx_titulo varchar(200) NOT NULL,
  mus_nu_minuto integer NOT NULL,
  PRIMARY KEY (mus_cd_id)
);

CREATE TABLE album_musica (
	mus_cd_id integer NOT NULL, 
	alb_cd_id integer NOT NULL,
	PRIMARY KEY (mus_cd_id, alb_cd_id),
	FOREIGN KEY (mus_cd_id) REFERENCES musica(mus_cd_id),
	FOREIGN KEY (alb_cd_id) REFERENCES album(alb_cd_id)
);

CREATE TABLE usuario (
	usu_cd_id integer NOT NULL,
	usu_tx_email varchar(200) NOT NULL,
	usu_tx_nome varchar(100) NOT NULL,
	usu_tx_senha varchar(200) NOT NULL,
	usu_tx_perfil varchar(20) NOT NULL,
	PRIMARY KEY (usu_cd_id)
);

CREATE UNIQUE INDEX idx_usuario_email ON usuario(usu_tx_email);

CREATE SEQUENCE seq_artista ;
CREATE SEQUENCE seq_album ;
CREATE SEQUENCE seq_musica ;
CREATE SEQUENCE seq_usuario ;
