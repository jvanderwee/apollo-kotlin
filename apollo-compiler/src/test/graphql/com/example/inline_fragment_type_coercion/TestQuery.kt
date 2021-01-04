// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.inline_fragment_type_coercion

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.CustomScalarAdapters.Companion.DEFAULT
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.internal.OperationRequestBodyComposer
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser
import com.apollographql.apollo.api.internal.Throws
import com.example.inline_fragment_type_coercion.adapter.TestQuery_ResponseAdapter
import kotlin.Boolean
import kotlin.String
import kotlin.Suppress
import okio.Buffer
import okio.BufferedSource
import okio.ByteString
import okio.IOException

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery : Query<TestQuery.Data, Operation.Variables> {
  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  override fun name(): OperationName = OPERATION_NAME

  override fun responseFieldMapper(): ResponseFieldMapper<Data> {
    return ResponseFieldMapper { reader ->
      TestQuery_ResponseAdapter.fromResponse(reader)
    }
  }

  @Throws(IOException::class)
  override fun parse(source: BufferedSource, customScalarAdapters: CustomScalarAdapters):
      Response<Data> {
    return SimpleOperationResponseParser.parse(source, this, customScalarAdapters)
  }

  @Throws(IOException::class)
  override fun parse(byteString: ByteString, customScalarAdapters: CustomScalarAdapters):
      Response<Data> {
    return parse(Buffer().write(byteString), customScalarAdapters)
  }

  @Throws(IOException::class)
  override fun parse(source: BufferedSource): Response<Data> {
    return parse(source, DEFAULT)
  }

  @Throws(IOException::class)
  override fun parse(byteString: ByteString): Response<Data> {
    return parse(byteString, DEFAULT)
  }

  override fun composeRequestBody(customScalarAdapters: CustomScalarAdapters): ByteString {
    return OperationRequestBodyComposer.compose(
      operation = this,
      autoPersistQueries = false,
      withQueryDocument = true,
      customScalarAdapters = customScalarAdapters
    )
  }

  override fun composeRequestBody(): ByteString = OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = false,
    withQueryDocument = true,
    customScalarAdapters = DEFAULT
  )

  override fun composeRequestBody(
    autoPersistQueries: Boolean,
    withQueryDocument: Boolean,
    customScalarAdapters: CustomScalarAdapters
  ): ByteString = OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = autoPersistQueries,
    withQueryDocument = withQueryDocument,
    customScalarAdapters = customScalarAdapters
  )

  /**
   * The query type, represents all of the entry points into our object graph
   */
  data class Data(
    /**
     * For testing fragment type coercion
     */
    val foo: Foo?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        TestQuery_ResponseAdapter.Data.toResponse(writer, this)
      }
    }

    /**
     * For testing fragment type coercion
     */
    interface Foo {
      val __typename: String

      val foo: String

      fun marshaller(): ResponseFieldMarshaller

      interface Bar : Foo {
        override val __typename: String

        override val foo: String

        val bar: String

        override fun marshaller(): ResponseFieldMarshaller
      }

      data class BarFoo(
        override val __typename: String,
        override val foo: String,
        override val bar: String
      ) : Foo, Bar {
        override fun marshaller(): ResponseFieldMarshaller {
          return ResponseFieldMarshaller { writer ->
            TestQuery_ResponseAdapter.Data.Foo.BarFoo.toResponse(writer, this)
          }
        }
      }

      data class OtherFoo(
        override val __typename: String,
        override val foo: String
      ) : Foo {
        override fun marshaller(): ResponseFieldMarshaller {
          return ResponseFieldMarshaller { writer ->
            TestQuery_ResponseAdapter.Data.Foo.OtherFoo.toResponse(writer, this)
          }
        }
      }

      companion object {
        fun Foo.asBar(): Bar? = this as? Bar
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "65c4fd857f5cbd2283f0783a3b3cefd9ead5abb159f5cc20922b0d8e04286662"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery {
          |  foo {
          |    __typename
          |    foo
          |    ... on Bar {
          |      bar
          |    }
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: OperationName = object : OperationName {
      override fun name(): String {
        return "TestQuery"
      }
    }
  }
}
