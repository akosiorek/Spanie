update USLUGA_DODATKOWA set cena = 30 where nazwa_uslugi='obiad';
update USLUGA_DODATKOWA set cena = 15 where nazwa_uslugi='sniadanie';
update USLUGA_DODATKOWA set cena = 25 where nazwa_uslugi='kolacja';


# nr pokoju, rodzaj pokoju, cena
# zalezy od sezonu
select nr_pokoju, p.rodzaj_pokoju, sp.cena_pokoju from SEZON s join SEZON_TYP_POKOJU sp on s.nr_sezonu = sp.nr_sezonu join POKOJE p on sp.rodzaj_pokoju = p.rodzaj_pokoju;
