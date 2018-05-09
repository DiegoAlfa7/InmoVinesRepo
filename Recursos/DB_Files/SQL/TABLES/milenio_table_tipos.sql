
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos`
--

CREATE TABLE `tipos` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `activa` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `tipos`:
--

--
-- Volcado de datos para la tabla `tipos`
--

INSERT INTO `tipos` (`id`, `nombre`, `activa`) VALUES
(1, 'Piso', 1),
(2, 'Casa/Chalet', 1),
(3, 'Oficina', 1),
(4, 'Nave', 1),
(5, 'Negocio', 1),
(6, 'Terreno', 1),
(8, 'Local Comercial', 1),
(9, 'Garaje', 1),
(10, 'Trastero', 1),
(11, 'Dúplex', 1),
(12, 'Áticos', 1);
