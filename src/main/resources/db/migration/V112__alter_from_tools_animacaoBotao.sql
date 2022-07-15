delete from tool_currency where tool_id = 11;
delete from tool where id = 11;

update tool set "name" = 'PDF', description = 'Disponibilize arquivos em PDF para download e solicite dados de acesso se desejar.' where "name" = 'Anexo PDF';
update tool set name='Banner Imagem' ,description = 'Crie banner de destaque, como anúncios, promoções, produtos, etc.' where "name" = 'Banner de Imagem';
update tool set "name"  = 'Vídeo' where "name" = 'Banner de Video';
update tool set name='Formulário Newsletter' where "name" = 'Boletim Informativo';
update tool set name='Perguntas Frequentes' where "name" = 'Perguntas Respondidas Frequentemente';

update tool set is_free = true where cod_name != 'SHOP';
update tool set reuse = true where cod_name in ('PDF', 'LINK', 'VIDEOBANNER', 'IMAGEBANNER', 'IMAGECAROUSEL');