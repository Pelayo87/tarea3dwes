/*PLANTAS*/
INSERT INTO `plantas`(`codigo`, `nombrecomun`, `nombrecientifico`, `precio`) VALUES ('ROSA', 'ROSA', 'Rosa chinensis', '10');
INSERT INTO `plantas`(`codigo`, `nombrecomun`, `nombrecientifico`, `precio`)  VALUES ('MARGARITA', 'MARGARITA', 'Bellis perennis', '10');
INSERT INTO `plantas`(`codigo`, `nombrecomun`, `nombrecientifico`, `precio`)  VALUES ('TULIPAN', 'TULIPAN', 'Tulipa gesneriana', '10');
INSERT INTO `plantas`(`codigo`, `nombrecomun`, `nombrecientifico`, `precio`)  VALUES ('ORQUIDEA', 'ORQUIDEA', 'Phalaenopsis amabilis', '10');
INSERT INTO `plantas`(`codigo`, `nombrecomun`, `nombrecientifico`, `precio`)  VALUES ('LILAS', 'LILAS', 'Syringa vulgaris', '10');
INSERT INTO `plantas`(`codigo`, `nombrecomun`, `nombrecientifico`, `precio`)  VALUES ('JAZMIN', 'JAZMIN', 'Jasminum sambac', '10');
INSERT INTO `plantas`(`codigo`, `nombrecomun`, `nombrecientifico`, `precio`)  VALUES ('GERANIO', 'GERANIO', 'Pelargonium peltatum', '10');
INSERT INTO `plantas`(`codigo`, `nombrecomun`, `nombrecientifico`, `precio`)  VALUES ('ALMENDRO', 'ALMENDRO', 'Prunus dulcis', '10');
INSERT INTO `plantas`(`codigo`, `nombrecomun`, `nombrecientifico`, `precio`)  VALUES ('MIMOSA', 'MIMOSA', 'Acacia dealbata', '10');
INSERT INTO `plantas`(`codigo`, `nombrecomun`, `nombrecientifico`, `precio`)  VALUES ('MAGNOLIA', 'MAGNOLIA', 'Magnolia grandiflora', '10');

/*EJEMPLARES*/
INSERT INTO `ejemplares`(`id_ejemplar`, `nombrecomun`, `codigo`, `disponible`) VALUES ('1','ROSA_1','ROSA', true);
INSERT INTO `ejemplares`(`id_ejemplar`, `nombrecomun`, `codigo`, `disponible`) VALUES ('2','MARGARITA_2','MARGARITA', true);
INSERT INTO `ejemplares`(`id_ejemplar`, `nombrecomun`, `codigo`, `disponible`) VALUES ('3','TULIPAN_3','TULIPAN', true);
INSERT INTO `ejemplares`(`id_ejemplar`, `nombrecomun`, `codigo`, `disponible`) VALUES ('4','ORQUIDEA_4','ORQUIDEA', true);
INSERT INTO `ejemplares`(`id_ejemplar`, `nombrecomun`, `codigo`, `disponible`) VALUES ('5','LILAS_5','LILAS', true);
INSERT INTO `ejemplares`(`id_ejemplar`, `nombrecomun`, `codigo`, `disponible`) VALUES ('6','JAZMIN_6','JAZMIN', true);
INSERT INTO `ejemplares`(`id_ejemplar`, `nombrecomun`, `codigo`, `disponible`) VALUES ('7','GERANIO_7','GERANIO', true);
INSERT INTO `ejemplares`(`id_ejemplar`, `nombrecomun`, `codigo`, `disponible`) VALUES ('8','ALMENDRO_8','ALMENDRO', true);
INSERT INTO `ejemplares`(`id_ejemplar`, `nombrecomun`, `codigo`, `disponible`) VALUES ('9','MIMOSA_9','MIMOSA', true);
INSERT INTO `ejemplares`(`id_ejemplar`, `nombrecomun`, `codigo`, `disponible`) VALUES ('10','MAGNOLIA_10','MAGNOLIA', true);

/*FITOSANITARIOS*/
INSERT INTO `fitosanitario`(`id_fitosanitario`, `eco`, `marca`, `nombre`) VALUES ('1','Eco-friendly','Bayer','Fitosanitario Alpha');
INSERT INTO `fitosanitario`(`id_fitosanitario`, `eco`, `marca`, `nombre`) VALUES ('2','Eco-safe','Syngenta','Fitosanitario Beta');
INSERT INTO `fitosanitario`(`id_fitosanitario`, `eco`, `marca`, `nombre`) VALUES ('3','Eco-plus','BASF','Fitosanitario Gamma');
INSERT INTO `fitosanitario`(`id_fitosanitario`, `eco`, `marca`, `nombre`) VALUES ('4','Eco-nature','Dow AgroSciences','Fitosanitario Delta');
INSERT INTO `fitosanitario`(`id_fitosanitario`, `eco`, `marca`, `nombre`) VALUES ('5','Eco-pure','Adama','Fitosanitario Epsilon');
INSERT INTO `fitosanitario`(`id_fitosanitario`, `eco`, `marca`, `nombre`) VALUES ('6','Eco-shield','Mitsui','Fitosanitario Zeta');
INSERT INTO `fitosanitario`(`id_fitosanitario`, `eco`, `marca`, `nombre`) VALUES ('7','Eco-balance','Corteva','Fitosanitario Eta');
INSERT INTO `fitosanitario`(`id_fitosanitario`, `eco`, `marca`, `nombre`) VALUES ('8','Eco-active','UPL','Fitosanitario Theta');
INSERT INTO `fitosanitario`(`id_fitosanitario`, `eco`, `marca`, `nombre`) VALUES ('9','Eco-smart','Nufarm','Fitosanitario Iota');
INSERT INTO `fitosanitario`(`id_fitosanitario`, `eco`, `marca`, `nombre`) VALUES ('10','Eco-dynamic','FMC','Fitosanitario Kappa');

/*MENSAJES*/
INSERT INTO `mensajes`(`id`, `fechahora`, `mensaje`, `id_ejemplar`, `id_persona`) VALUES ('1','2025-02-03 08:50:30.000000','Registro realizado por admin el Mon Feb 03 08:50:30.000000','1','1');
INSERT INTO `mensajes`(`id`, `fechahora`, `mensaje`, `id_ejemplar`, `id_persona`) VALUES ('2','2025-02-03 08:51:30.000000','Registro realizado por admin el Mon Feb 03 08:51:30.000000','2','1');
INSERT INTO `mensajes`(`id`, `fechahora`, `mensaje`, `id_ejemplar`, `id_persona`) VALUES ('3','2025-02-03 08:52:30.000000','Registro realizado por admin el Mon Feb 03 08:52:30.000000','3','1');
INSERT INTO `mensajes`(`id`, `fechahora`, `mensaje`, `id_ejemplar`, `id_persona`) VALUES ('4','2025-02-03 08:53:30.000000','Registro realizado por admin el Mon Feb 03 08:53:30.000000','4','1');
INSERT INTO `mensajes`(`id`, `fechahora`, `mensaje`, `id_ejemplar`, `id_persona`) VALUES ('5','2025-02-03 08:54:30.000000','Registro realizado por admin el Mon Feb 03 08:54:30.000000','5','1');
INSERT INTO `mensajes`(`id`, `fechahora`, `mensaje`, `id_ejemplar`, `id_persona`) VALUES ('6','2025-02-03 08:55:30.000000','Registro realizado por admin el Mon Feb 03 08:55:30.000000','6','1');
INSERT INTO `mensajes`(`id`, `fechahora`, `mensaje`, `id_ejemplar`, `id_persona`) VALUES ('7','2025-02-03 08:56:30.000000','Registro realizado por admin el Mon Feb 03 08:56:30.000000','7','1');
INSERT INTO `mensajes`(`id`, `fechahora`, `mensaje`, `id_ejemplar`, `id_persona`) VALUES ('8','2025-02-03 08:57:30.000000','Registro realizado por admin el Mon Feb 03 08:57:30.000000','8','1');
INSERT INTO `mensajes`(`id`, `fechahora`, `mensaje`, `id_ejemplar`, `id_persona`) VALUES ('9','2025-02-03 08:58:30.000000','Registro realizado por admin el Mon Feb 03 08:58:30.000000','9','1');
INSERT INTO `mensajes`(`id`, `fechahora`, `mensaje`, `id_ejemplar`, `id_persona`) VALUES ('10','2025-02-03 08:59:30.000000','Registro realizado por admin el Mon Feb 03 08:59:30.000000','10','1');

