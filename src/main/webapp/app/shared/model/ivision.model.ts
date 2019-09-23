import { Moment } from 'moment';

export interface IIvision {
  id?: number;
  profile_photo?: number;
  nickname?: string;
  gender?: string;
  birthday?: Moment;
  telphone?: string;
  mailbox?: string;
}

export const defaultValue: Readonly<IIvision> = {};
