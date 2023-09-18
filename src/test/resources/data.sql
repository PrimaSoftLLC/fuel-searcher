INSERT INTO table_metadata(table_name)
VALUES(1, 'ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ'),
      (2, 'ВСПАШКА СТЕРНИ');

INSERT INTO property_metadata(id, table_metadata_id, property_name, allowable_values)
VALUES(1, 1, 'удельное сопротивление', ARRAY('значение-1', 'значение-2')),
      (2, 1, 'трактор', ARRAY('значение-1', 'значение-2', 'значение-3'));

INSERT INTO property_metadata(id, table_metadata_id, property_name, allowable_values)
VALUES(3, 2, 'тип удобрения', ARRAY('значение-1', 'значение-2')),
      (4, 2, 'норма внесения', ARRAY('значение-1', 'значение-2', 'значение-3'));
