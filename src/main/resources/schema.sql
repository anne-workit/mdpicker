CREATE TABLE public.category (
   category_id integer auto_increment NOT NULL,
   category_name varchar NULL,
   CONSTRAINT category_pk PRIMARY KEY (category_id)
);

CREATE TABLE public.brand (
    brand_id integer auto_increment NOT NULL,
    brand_name varchar(10) NOT NULL,
    CONSTRAINT brand_pk PRIMARY KEY (brand_id)
);

CREATE TABLE public.price (
    price_id integer auto_increment NOT NULL,
    brand_id integer NOT NULL,
    category_id integer NOT NULL,
    price integer NOT NULL
);

ALTER TABLE public.price ADD CONSTRAINT price_fk FOREIGN KEY (brand_id) REFERENCES public.brand(brand_id);
ALTER TABLE public.price ADD CONSTRAINT price_fk_1 FOREIGN KEY (category_id) REFERENCES public.category(category_id);