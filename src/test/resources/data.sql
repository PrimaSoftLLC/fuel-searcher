INSERT INTO table_metadata(id, table_name)
VALUES(255, 'ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ'),
      (256, 'ВСПАШКА СТЕРНИ');

INSERT INTO property_metadata(id, table_metadata_id, property_name, allowable_values)
VALUES(255, 255, 'удельное сопротивление', ARRAY['значение-1', 'значение-2']),
      (256, 255, 'трактор', ARRAY['значение-1', 'значение-2', 'значение-3']);

INSERT INTO property_metadata(id, table_metadata_id, property_name, allowable_values)
VALUES(257, 256, 'тип удобрения', ARRAY['значение-1', 'значение-2']),
      (258, 256, 'норма внесения', ARRAY['значение-1', 'значение-2', 'значение-3']);
