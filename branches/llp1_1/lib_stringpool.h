#ifndef  _LIB_STRINGPOOL_H_
#define  _LIB_STRINGPOOL_H_



typedef struct _string_pool string_pool;

string_pool* lib_stringpool_new();
void lib_stringpool_free(string_pool* sp);
char* lib_stringpool_add(string_pool* sp, const char* str, size_t s_len);
void dump_stringpool(string_pool* sp);

#endif