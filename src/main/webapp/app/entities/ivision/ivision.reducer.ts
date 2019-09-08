import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IIvision, defaultValue } from 'app/shared/model/ivision.model';

export const ACTION_TYPES = {
  FETCH_IVISION_LIST: 'ivision/FETCH_IVISION_LIST',
  FETCH_IVISION: 'ivision/FETCH_IVISION',
  CREATE_IVISION: 'ivision/CREATE_IVISION',
  UPDATE_IVISION: 'ivision/UPDATE_IVISION',
  DELETE_IVISION: 'ivision/DELETE_IVISION',
  RESET: 'ivision/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IIvision>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type IvisionState = Readonly<typeof initialState>;

// Reducer

export default (state: IvisionState = initialState, action): IvisionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_IVISION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_IVISION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_IVISION):
    case REQUEST(ACTION_TYPES.UPDATE_IVISION):
    case REQUEST(ACTION_TYPES.DELETE_IVISION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_IVISION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_IVISION):
    case FAILURE(ACTION_TYPES.CREATE_IVISION):
    case FAILURE(ACTION_TYPES.UPDATE_IVISION):
    case FAILURE(ACTION_TYPES.DELETE_IVISION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_IVISION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_IVISION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_IVISION):
    case SUCCESS(ACTION_TYPES.UPDATE_IVISION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_IVISION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/ivisions';

// Actions

export const getEntities: ICrudGetAllAction<IIvision> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_IVISION_LIST,
    payload: axios.get<IIvision>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IIvision> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_IVISION,
    payload: axios.get<IIvision>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IIvision> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_IVISION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IIvision> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_IVISION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IIvision> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_IVISION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
