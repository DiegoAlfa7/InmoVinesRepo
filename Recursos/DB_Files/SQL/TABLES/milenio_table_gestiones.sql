
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gestiones`
--

CREATE TABLE `gestiones` (
  `id` smallint(6) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `gestiones`:
--

--
-- Volcado de datos para la tabla `gestiones`
--

INSERT INTO `gestiones` (`id`, `nombre`) VALUES
(1, 'Venta'),
(2, 'Alquiler'),
(3, 'Traspaso'),
(4, 'Alquiler con opción a compra'),
(5, 'Alquiler Vacacional');
