create table if not exists book_instance
(
    id         bigserial primary key,
    uuid       uuid not null,
    book_id    bigint,
    library_id bigint,
    constraint fk_book foreign key (book_id) references book (id),
    constraint fk_library foreign key (library_id) references library (id)
)