
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tareas`
--

CREATE TABLE `tareas` (
  `id` bigint(20) NOT NULL,
  `id_demanda` bigint(20) NOT NULL,
  `id_agente` bigint(20) UNSIGNED NOT NULL,
  `concepto` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha` date NOT NULL,
  `completada` tinyint(1) DEFAULT NULL,
  `fecha_completada` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `tareas`:
--

--
-- Volcado de datos para la tabla `tareas`
--

INSERT INTO `tareas` (`id`, `id_demanda`, `id_agente`, `concepto`, `descripcion`, `fecha`, `completada`, `fecha_completada`) VALUES
(1, 1, 1, 'llamar', 'ddDSAADADSA', '2018-01-24', 1, '2018-01-16'),
(2, 2, 1, 'LLAMAR', 'DSDSDA', '2018-01-16', 0, NULL);
