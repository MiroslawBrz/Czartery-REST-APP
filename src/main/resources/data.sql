INSERT INTO public.user_roles (description, role) VALUES
('Administrator', 'ADMIN_ROLE'),
('User', 'USER_ROLE');


INSERT INTO public.charter_user (user_name, user_last_name, user_mail, user_password, user_active, activation_hash, user_role_id)
VALUES
('Adam', 'Kowalski', 'adamkowalski@buziaczek.com', 'krjsHgr89824vjh#bt58', true, 987, 1),
('Janusz', 'Nosacz', 'janusz@gmail.com','iuuh#sD9361/di8v', false, 789, 2),
('Jan', 'Nowak', 'nowak@wp.pl', 'sdvs8F10vb!dbrd', true, 456, 2);

INSERT INTO public.charter_place_address (address_building_number, address_city, address_postal_code, address_street, map_latitude, map_longitude)
VALUES
('10', 'Warszawa', 'Kochanowskiego', '91-210', 15.621, 12.351),
('11', 'Płock', 'Chemików', '01-230', 15.622, 12.974);

INSERT INTO public.charter_place (charter_place_name, web_site_url, user_id)
VALUES
('Warszawski Klub Żeglarski', 'www.wkz.waw.pl', 2),
('Płocki Klub Żeglarski', 'zeglarskiploc.pl', 2);

INSERT INTO public.yacht(price_per_day, price_per_week, yacht_capacity, yacht_description,
                         yacht_length, yacht_name, yacht_type, charter_place_id)
VALUES
(120.0, 700.0, 6, 'Rodzinny jacht kabinowy', 8.6, 'Szkwał', 'Tango780', 2),
(100.0, 600.0, 3, 'Odkrytopokładowa żaglówka', 6.2, 'Pixel', 'Omega', 2),
(150.0, 800.0, 8, 'Rodzinny jacht kabinowy', 9.1, 'Grażyna', 'Sasanka', 1)
