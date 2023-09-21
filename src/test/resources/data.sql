INSERT INTO table_metadata(id, table_name)
VALUES(255, 'ПЕРВАЯ ТАБЛИЦА'),
      (256, 'ВТОРАЯ ТАБЛИЦА');

INSERT INTO property_metadata(id, table_metadata_id, property_name, allowable_values)
VALUES(255, 255, 'первое свойство', ARRAY['значение-1', 'значение-2']),
      (256, 255, 'второе ствойство', ARRAY['значение-1', 'значение-2', 'значение-3']);

INSERT INTO property_metadata(id, table_metadata_id, property_name, allowable_values)
VALUES(257, 256, 'третье свойство', ARRAY['значение-1', 'значение-2']),
      (258, 256, 'четвертое свойство', ARRAY['значение-1', 'значение-2', 'значение-3']);
